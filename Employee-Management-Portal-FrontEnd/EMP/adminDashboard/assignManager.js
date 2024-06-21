document.addEventListener('DOMContentLoaded', function () {
    const assignManagerBtn = document.getElementById('assign-manager-btn');
    const cancelBtn = document.getElementById('cancel-btn');
    const projectSelect = document.getElementById('project-select');
    const managerSelect = document.getElementById('manager-select');
    const errorProject = document.getElementById('error-project');
    const errorManager = document.getElementById('error-manager');

    // const empId = localStorage.getItem('empId');
    const jwtToken = localStorage.getItem('jwtToken');

    if (!jwtToken) {
        showCustomAlert('Please login first.', function() {
            window.location.href = '/login.html'; // Redirect to login page
        });
        return;
    }

    const payload = JSON.parse(atob(jwtToken.split('.')[1]));
    const userRole = payload.authorities;

    if (userRole !== 'ADMIN') {
        showCustomAlert('You do not have permission to access this page.', function() {
            window.location.href = '/login.html'; // Redirect to login page
        });
        return;
    }

    // Fetch project list and populate dropdown
    fetch('http://localhost:8080/employee/projectsWithoutManager', {
        headers: {
            'Authorization': `Bearer ${jwtToken}`
        }
    })
    .then(response => response.json())
    .then(data => {
        data.forEach(project => {
            const option = document.createElement('option');
            option.value = project.id;
            option.textContent = `${project.projectName} (${project.startDate})`;
            projectSelect.appendChild(option);
        });
    })
    .catch(error => console.error('Error fetching projects:', error));

    // Fetch manager list and populate dropdown
    fetch('http://localhost:8080/employee/getAllManagersInfo', {
        headers: {
            'Authorization': `Bearer ${jwtToken}`
        }
    })
    .then(response => response.json())
    .then(data => {
        data.forEach(manager => {
            const option = document.createElement('option');
            option.value = manager.managerEmployeeId;
            option.textContent = `${manager.managerName} (${manager.managerEmployeeId})`;
            managerSelect.appendChild(option);
        });
    })
    .catch(error => console.error('Error fetching managers:', error));

    // Handle assign manager button click
    assignManagerBtn.addEventListener('click', function () {
        const selectedProjectId = projectSelect.value;
        const selectedManagerId = managerSelect.value;

        if (!selectedProjectId) {
            errorProject.textContent = 'Project is required';
            return;
        }

        if (!selectedManagerId) {
            errorManager.textContent = 'Manager is required';
            return;
        }

        errorProject.textContent = '';
        errorManager.textContent = '';

        const assignManagerData = {
            projectId: selectedProjectId,
            managerId: selectedManagerId
        };

        fetch('http://localhost:8080/employee/assignManager', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwtToken}`
            },
            body: JSON.stringify(assignManagerData)
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            showCustomAlert(data.message, function() {
                window.location.href = 'adminDashboardProject.html'; // Redirect to admin dashboard projects page
            });
        })
        .catch(error => console.error('Error assigning manager:', error));
    });

    // Handle cancel button click
    cancelBtn.addEventListener('click', function () {
        window.location.href = 'adminDashboardProject.html'; // Redirect to admin dashboard projects page
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
