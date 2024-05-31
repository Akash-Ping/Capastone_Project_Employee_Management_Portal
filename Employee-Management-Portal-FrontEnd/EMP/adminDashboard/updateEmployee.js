document.addEventListener('DOMContentLoaded', function () {
    const updateForm = document.getElementById('updateEmployeeForm'); // Corrected form ID
    const cancelBtn = document.getElementById('cancel-btn');
    const employee = JSON.parse(localStorage.getItem('employeeToUpdate'));
    const jwtToken = localStorage.getItem('jwtToken');

    if (!jwtToken) {
        alert('Please login first.');
        window.location.href = '/login.html'; // Redirect to login page
        return;
    }

    const payload = JSON.parse(atob(jwtToken.split('.')[1]));
    const userRole = payload.authorities;

    if (userRole !== 'ADMIN') {
        alert('You do not have permission to access this page.');
        window.location.href = '/login.html'; // Redirect to login page
        return;
    }

    if (employee) {
        document.getElementById('empId').value = employee.empId;
        document.getElementById('name').value = employee.name;
        document.getElementById('contactNo').value = employee.contactNo;
        document.getElementById('dob').value = formatDate(employee.dob); // Format dob
        document.getElementById('doj').value = formatDate(employee.doj); // Format doj
        document.getElementById('designation').value = employee.designation;
        document.getElementById('email').value = employee.email;
        document.getElementById('location').value = employee.location;
    }

    // Function to format date (assuming input format is 'MM/DD/YYYY')
    function formatDate(dateString) {
        const date = new Date(dateString);
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`; // Format: YYYY-MM-DD
    }

    // Cities list for the dropdown
    const cities = [
        'Mumbai', 'Delhi', 'Bangalore', 'Hyderabad', 'Ahmedabad',
        'Chennai', 'Kolkata', 'Pune', 'Jaipur', 'Lucknow', 'Kanpur',
        'Nagpur', 'Indore', 'Thane', 'Bhopal', 'Visakhapatnam',
        'Pimpri-Chinchwad', 'Patna', 'Vadodara', 'Ghaziabad'
    ];

    const locationSelect = document.getElementById('location');
    cities.forEach(city => {
        const option = document.createElement('option');
        option.value = city;
        option.textContent = city;
        locationSelect.appendChild(option);
    });

    // Handle form submission for updating employee details
    updateForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const updatedEmployeeData = {
            empId: employee.empId,
            name: document.getElementById('name').value,
            contactNo: document.getElementById('contactNo').value,
            dob: document.getElementById('dob').value,
            doj: document.getElementById('doj').value,
            designation: document.getElementById('designation').value,
            email: document.getElementById('email').value,
            location: document.getElementById('location').value
        };

        fetch('http://localhost:8080/employee/update', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwtToken}`
            },
            body: JSON.stringify(updatedEmployeeData)
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            window.location.href = 'adminDashboard.html'; // Redirect to admin dashboard
        })
        .catch(error => console.error('Error updating employee:', error));
    });

    // Handle cancel button click
    cancelBtn.addEventListener('click', function () {
        window.location.href = 'adminDashboard.html'; // Redirect to admin dashboard
    });
});
