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
        history.pushState(null, null, location.href); // Push a new state for register form
    });

    // Event listener for going back to the login form from the registration form
    backToLoginButton.addEventListener('click', function (event) {
        event.preventDefault();
        registerContainer.style.display = 'none';
        loginContainer.style.display = 'block';
        history.pushState(null, null, location.href); // Push a new state for login form
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
        .then(response => {
            if (!response.ok) {
                throw new Error('Invalid Credentials');
            }
            return response.json();
        })
        .then(data => {
            if (data.message === "Login successful") {
                localStorage.setItem("isLoggedIn", "true");
                localStorage.setItem("jwtToken", data.jwt);
                console.log(data);
                const jwtToken = localStorage.getItem('jwtToken');
                const payload = JSON.parse(atob(jwtToken.split('.')[1]));
                const userRole = payload.authorities;

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
                        showCustomAlert('Unknown role: ' + data.role);
                }
            } else {
                showCustomAlert(data.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            showCustomAlert('Invalid Credentials');
        });
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

        const empIdRegex = /^N\d{3}$/;
        if (!empIdRegex.test(empId)) {
            showCustomAlert("Employee ID should be in the format 'NXXX'");
            return;
        }

        const contactNoRegex = /^\d{10}$/;
        if (!contactNoRegex.test(contactNo)) {
            showCustomAlert("Contact number should be a 10-digit number.");
            return;
        }

        if (password !== confirmPassword) {
            showCustomAlert("Passwords do not match");
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
            if (data.message === "Admin registered successfully") {
                showCustomAlert('Registration successful. Please login.');
                registerContainer.style.display = 'none';
                loginContainer.style.display = 'block';
                history.pushState(null, null, location.href); // Push a new state after registration
            } else if (data.message === "Admin already registered") {
                showCustomAlert('Admin already registered. Please login.');
            } else {
                showCustomAlert('Registration failed: ' + data.message);
            }
        })
        .catch(error => console.error('Error:', error));
    });

    const jwtToken = localStorage.getItem('jwtToken');
    if (jwtToken) {
        const payload = JSON.parse(atob(jwtToken.split('.')[1]));
        const userRole = payload.authorities;
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
    }

    function showCustomAlert(message, callback) {
        const alertOverlay = document.getElementById('custom-alert-overlay');
        const alertMessage = document.getElementById('custom-alert-message');

        alertMessage.textContent = message;
        alertOverlay.style.display = 'flex';

        const closeHandler = function() {
            alertOverlay.style.display = 'none';
            if (callback) callback();
        };

        document.getElementById('custom-alert').querySelector('button').onclick = closeHandler;
    }

    // Disable the back and forward buttons
    history.pushState(null, null, location.href);
    window.onpopstate = function () {
        history.pushState(null, null, location.href);
    };
});
