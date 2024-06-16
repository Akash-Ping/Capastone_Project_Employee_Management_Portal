document.addEventListener('DOMContentLoaded', function () {
    const jwtToken = localStorage.getItem('jwtToken');

    if (!jwtToken) {
        showCustomAlert('Session expired or invalid access.',function(){
            window.location.href = '/login.html'; // Redirect to login page
            });
            return;
    }

    // Decode the JWT token to get the payload
    const payload = JSON.parse(atob(jwtToken.split('.')[1]));

    // Extract the user role from the payload
    const userRole = payload.authorities; // Assuming the role is stored in the token
    if (userRole !== 'EMPLOYEE') {
        showCustomAlert('You do not have permission to access this page.',function() {
            window.location.href = '/login.html'; // Redirect to login page
            });
            return;
    }

    const userEmail = payload.email;

    if (!userEmail) {
        showCustomAlert('User email not found.',function(){
            window.location.href = '/login.html'; // Redirect to login page
            });
        return;
    }

    fetchEmployeeDetails(userEmail);

    function fetchEmployeeDetails(email) {
        fetch(`http://localhost:8080/api/employee/${email}`, {
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        })
            .then(response => response.json())
            .then(data => {
                displayEmployeeDetails(data);
            })
            .catch(error => console.error('Error fetching employee details:', error));
    }

    function displayEmployeeDetails(employee) {
        document.getElementById('emp-id').textContent = employee.empId;
        document.getElementById('emp-name').textContent = employee.name;
        document.getElementById('emp-doj').textContent = formatDate(employee.doj);
        document.getElementById('emp-manager').textContent = employee.managerName || 'N/A';
        document.getElementById('emp-email').textContent = employee.email;
        document.getElementById('emp-contact').textContent = employee.contactNo;
        document.getElementById('emp-project').textContent = employee.projectName || 'N/A';
        document.getElementById('emp-designation').textContent = employee.designation;
        document.getElementById('emp-location').textContent = employee.location;
        document.getElementById('emp-skills').textContent = employee.assignedSkills.join(', ');

        // Store employee ID in localStorage
        // localStorage.setItem('empId', employee.empId);
    }

    function formatDate(dateString) {
        const date = new Date(dateString);
        return `${date.getDate()}/${date.getMonth() + 1}/${date.getFullYear()}`;
    }

    // Add event listener to the update skills button
    document.getElementById('update-skills-btn').addEventListener('click', function() {
        window.location.href = 'updateSkills.html';
    });

    // Event listener for Change Password button
    document.getElementById('change-password-btn').addEventListener('click', function() {
        document.getElementById('password-popup').classList.add('active');
        document.querySelector('.popup-content').classList.add('active');
    });

    // Event listener for Close Popup button and overlay
    document.querySelector('.overlay').addEventListener('click', function() {
        closePopup();
    });

    document.getElementById('close-popup').addEventListener('click', function() {
        closePopup();
    });

    // Function to close the popup
    function closePopup() {
        document.getElementById('password-popup').classList.remove('active');
        document.querySelector('.popup-content').classList.remove('active');
    }

    // Event listener for Show Password checkbox
    document.getElementById('show-password-checkbox').addEventListener('change', function() {
        const newPasswordInput = document.getElementById('new-password');
        if (this.checked) {
            newPasswordInput.type = 'text';
        } else {
            newPasswordInput.type = 'password';
        }
    });

    // Event listener for Update Password button
    document.getElementById('update-password-btn').addEventListener('click', function() {
        const newPassword = document.getElementById('new-password').value;
        if (newPassword && userEmail) {
            updatePassword(userEmail, newPassword);
        }
    });

    // Function to update password via API
    function updatePassword(email, newPassword) {
        fetch(`http://localhost:8080/api/employee/${email}/updatepassword`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwtToken}`
            },
            body: JSON.stringify({ password: newPassword })
        })
        .then(response => {
            if (response.ok) {
                showCustomAlert('Password updated successfully.');
                closePopup(); // Close popup
            } else {
                console.error('Failed to update password.');
            }
        })
        .catch(error => console.error('Error:', error));
    }

    // Sidebar navigation
    const sidebarItems = document.querySelectorAll('.sidebar-ele li');
    sidebarItems.forEach(item => {
        item.addEventListener('click', function () {
            const pageName = this.getAttribute('data-page');
            window.location.href = pageName; // Redirect based on the data-page attribute
        });
    });

    // Logout button functionality
    const logoutButton = document.querySelector('.logout-btn');
    logoutButton.addEventListener('click', function () {
        localStorage.clear(); // Clear all localStorage items
        window.location.href = '/login.html'; // Redirect to login page
    });

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
});
