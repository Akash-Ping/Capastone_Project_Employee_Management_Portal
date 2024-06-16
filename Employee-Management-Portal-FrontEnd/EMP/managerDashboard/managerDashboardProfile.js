document.addEventListener('DOMContentLoaded', function () {
    const jwtToken = localStorage.getItem('jwtToken');

    if (!jwtToken) {
        showCustomAlert('Session expired or invalid access.',function(){
            window.location.href = '/login.html'; // Redirect to login page
            });
            return;
    }

    const payload = JSON.parse(atob(jwtToken.split('.')[1]));
    const userRole = payload.authorities;

    if (userRole !== 'MANAGER') {
        showCustomAlert('You do not have permission to access this page.',function() {
            window.location.href = '/login.html'; // Redirect to login page
            });
            return;
    }

    const userEmail = payload.email;

    if (!userEmail) {
        showCustomAlert('User email not found.');
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
        document.getElementById('emp-email').textContent = employee.email;
        document.getElementById('emp-contact').textContent = employee.contactNo;
        document.getElementById('emp-designation').textContent = employee.designation;
        document.getElementById('emp-location').textContent = employee.location;
        document.getElementById('emp-skills').textContent = employee.assignedSkills.join(', ');

        localStorage.setItem('empId', employee.empId);
    }

    function formatDate(dateString) {
        const date = new Date(dateString);
        return `${date.getDate()}/${date.getMonth() + 1}/${date.getFullYear()}`;
    }

    const sidebarItems = document.querySelectorAll('.sidebar-ele li');
    sidebarItems.forEach(item => {
        item.addEventListener('click', function () {
            const pageName = this.getAttribute('data-page');
            window.location.href = pageName;
        });
    });

    const logoutButton = document.querySelector('.logout-btn');
    logoutButton.addEventListener('click', function () {
        localStorage.clear();
        window.location.href = '/login.html';
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
