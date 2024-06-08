document.addEventListener('DOMContentLoaded', function () {
    const loginContainer = document.getElementById('loginContainer');
    const registerContainer = document.getElementById('registerContainer');
    const showRegisterFormLink = document.getElementById('showRegisterForm');
    const backToLoginButton = document.getElementById('backToLogin');
    const locationSelect = document.getElementById('registerLocation');

    // List of cities for the dropdown
    const cities = [
        'Mumbai', 'Delhi', 'Bangalore', 'Hyderabad', 'Ahmedabad',
        'Chennai', 'Kolkata', 'Pune', 'Jaipur', 'Lucknow', 'Kanpur',
        'Nagpur', 'Indore', 'Thane', 'Bhopal', 'Visakhapatnam',
        'Pimpri-Chinchwad', 'Patna', 'Vadodara', 'Ghaziabad'
    ];

    // Populate the location dropdown
    cities.forEach(city => {
        const option = document.createElement('option');
        option.value = city;
        option.textContent = city;
        locationSelect.appendChild(option);
    });

    // Event listener for showing the registration form
    showRegisterFormLink.addEventListener('click', function (event) {
        event.preventDefault();
        loginContainer.style.display = 'none';
        registerContainer.style.display = 'block';
    });

    // Event listener for going back to the login form from the registration form
    backToLoginButton.addEventListener('click', function (event) {
        event.preventDefault();
        registerContainer.style.display = 'none';
        loginContainer.style.display = 'block';
    });

    // Event listener for submitting the login form
    document.getElementById('loginForm').addEventListener('submit', function (event) {
        event.preventDefault();
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        fetch('http://localhost:8080/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, password })
        })
        // .then(response => response.json())
        .then(response => {
            if (!response.ok) {
                throw new Error('Invalid Credentials');
            }
            return response.json();
        })
        .then(data => {
            if (data.message === "Login successful") {
                // Store the user role in local storage
                localStorage.setItem("isLoggedIn", "true");
                localStorage.setItem("jwtToken", data.jwt);
                // localStorage.setItem('sessionUserRole', data.role);
                // localStorage.setItem('sessionUserName', data.name);
                // localStorage.setItem('sessionUserEmail', data.email);
                console.log(data);

                const jwtToken = localStorage.getItem('jwtToken');

                // Decode the JWT token to get the payload
                 const payload = JSON.parse(atob(jwtToken.split('.')[1]));

    // Extract the user role from the payload
                const userRole = payload.authorities; // Assuming the role is stored in the token
    

                // Redirect based on role
                switch (userRole) {
                    case 'ADMIN':
                        window.location.href = '/EMP/adminDashboard/adminDashboard.html';
                        break;
                    case 'MANAGER':
                        window.location.href = '/EMP/managerDashboard/managerDashboardProfile.html';
                        break;
                    case 'EMPLOYEE':
                        window.location.href = '/EMP/employeeDashboard/employeeDashboard.html';
                        break;
                    default:
                        alert('Unknown role: ' + data.role);
                }
            } else {
                alert(data.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Invalid Credentials');
        });
        // .catch(error => console.error('Error:', error));
    });

    // Event listener for submitting the registration form
    document.getElementById('registerForm').addEventListener('submit', function (event) {
        event.preventDefault();

        const empId = document.getElementById('registerEmpId').value;
        const name = document.getElementById('registerName').value;
        const email = document.getElementById('registerEmail').value;
        const dob = document.getElementById('registerDob').value;
        const doj = document.getElementById('registerDoj').value;
        const location = document.getElementById('registerLocation').value;
        const designation = document.getElementById('registerDesignation').value;
        const contactNo = document.getElementById('registerContact').value;
        const password = document.getElementById('registerPassword').value;
        const confirmPassword = document.getElementById('registerConfirmPassword').value;

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

        if (password !== confirmPassword) {
            alert("Passwords do not match");
            return;
        }

        fetch('http://localhost:8080/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                empId, name, email, dob, doj, location, designation, contactNo, password
            })
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            if (data.message === "Admin registered successfully") {
                alert('Registration successful. Please login.');
                registerContainer.style.display = 'none';
                loginContainer.style.display = 'block';
            } else if (data.message === "Admin already registered") {
                alert('Admin already registered. Please login.');
            } else {
                alert('Registration failed: ' + data.message);
            }
        })
        .catch(error => console.error('Error:', error));
    });

    // Redirect to the appropriate dashboard if user is already logged in
    // const userRole = localStorage.getItem('sessionUserRole');

    const jwtToken = localStorage.getItem('jwtToken');

    // Decode the JWT token to get the payload
     const payload = JSON.parse(atob(jwtToken.split('.')[1]));

// Extract the user role from the payload
    const userRole = payload.authorities; // Assuming the role is stored in the token

    if (userRole) {
        switch (userRole) {
            case 'ADMIN':
                window.location.href = '/EMP/adminDashboard/adminDashboard.html';
                break;
            case 'MANAGER':
                window.location.href = '/EMP/managerDashboard/managerDashboardProfile.html';
                break;
            case 'EMPLOYEE':
                window.location.href = '/EMP/employeeDashboard/employeeDashboard.html';
                break;
        }
    }
});
