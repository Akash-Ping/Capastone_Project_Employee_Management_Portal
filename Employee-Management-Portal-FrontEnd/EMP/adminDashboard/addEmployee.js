document.addEventListener('DOMContentLoaded', function () {
    const addEmployeeForm = document.getElementById('addEmployeeForm');
    const cancelBtn = document.getElementById('cancel-btn');
    const skillsSelect = document.getElementById('assignedSkills');
    const locationSelect = document.getElementById('location');
    let selectedSkills = new Set();

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

    // Cities list for the dropdown
    const cities = [
        'Mumbai', 'Delhi', 'Bangalore', 'Hyderabad', 'Ahmedabad',
        'Chennai', 'Kolkata', 'Pune', 'Jaipur', 'Lucknow', 'Kanpur',
        'Nagpur', 'Indore', 'Thane', 'Bhopal', 'Visakhapatnam',
        'Pimpri-Chinchwad', 'Patna', 'Vadodara', 'Ghaziabad'
    ];

    cities.forEach(city => {
        const option = document.createElement('option');
        option.value = city;
        option.textContent = city;
        locationSelect.appendChild(option);
    });

    // Fetch skills and populate the dropdown
    fetch('http://localhost:8080/employee/allSkills', {
        headers: {
            'Authorization': `Bearer ${jwtToken}`
        }
    })
        .then(response => response.json())
        .then(data => {
            data.forEach(skill => {
                const option = document.createElement('option');
                option.value = skill.id;
                option.textContent = skill.skillName;
                skillsSelect.appendChild(option);
            });
        })
        .catch(error => console.error('Error fetching skills:', error));

    // Custom multi-select behavior
    skillsSelect.addEventListener('mousedown', function (event) {
        event.preventDefault();

        const option = event.target;
        const optionValue = parseInt(option.value);

        if (selectedSkills.has(optionValue)) {
            selectedSkills.delete(optionValue);
            option.selected = false;
        } else {
            selectedSkills.add(optionValue);
            option.selected = true;
        }
    });

    // Handle form submission
    addEmployeeForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const empId = document.getElementById('empId').value;
        const contactNo = document.getElementById('contactNo').value;
        const email = document.getElementById('email').value;

        // Validate employee ID format
        const empIdRegex = /^N\d{3}$/; // Regular expression for "NXXX" format
        if (!empIdRegex.test(empId)) {
            alert("Employee ID should be in the format 'NXXX'");
            return;
        }

        // Validate contact number format (10 digits)
        const contactNoRegex = /^\d{10}$/; // Regular expression for 10 digits
        if (!contactNoRegex.test(contactNo)) {
            alert("Contact number should be a 10-digit number.");
            return;
        }

        // Validate email format
        const emailRegex = /^[^\s@]+@nucleusteq\.com$/; // Regular expression for emails ending with @nucleusteq.com
        if (!emailRegex.test(email)) {
            alert("Email should end with '@nucleusteq.com'.");
            return;
        }

        const newEmployeeData = {
            name: document.getElementById('name').value,
            contactNo: contactNo,
            dob: document.getElementById('dob').value,
            doj: document.getElementById('doj').value,
            designation: document.getElementById('designation').value,
            email: email,
            empId: empId,
            location: document.getElementById('location').value,
            password: document.getElementById('password').value,
            role: document.getElementById('role').value,
            assignedSkills: Array.from(selectedSkills)
        };

        fetch('http://localhost:8080/employee/addUser', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwtToken}`
            },
            body: JSON.stringify(newEmployeeData)
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            if (data.message === "Employee added successfully") {
                alert('Employee added successfully.');
                window.location.href = 'adminDashboard.html'; // Redirect to admin dashboard
            } else {
                alert('Error adding employee: ' + data.message);
            }
        })
        .catch(error => {
            console.error('Error adding employee:', error);
            alert('Error adding employee. Please try again.');
        });
    });

    // Handle cancel button click
    cancelBtn.addEventListener('click', function () {
        window.location.href = 'adminDashboard.html'; // Redirect to admin dashboard
    });
});
