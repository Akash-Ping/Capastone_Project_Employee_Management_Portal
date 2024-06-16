document.addEventListener('DOMContentLoaded', function () {
    const assignProjectBtn = document.getElementById('assign-project-btn');
    const cancelBtn = document.getElementById('cancel-btn');
    const projectSelect = document.getElementById('project-select');
    const errorProject = document.getElementById('error-project');

    const empId = localStorage.getItem('empId');
    const jwtToken = localStorage.getItem('jwtToken');

    if (!jwtToken) {
        showCustomAlert('Please login first.',function() {
            window.location.href = '/login.html'; // Redirect to login page
            });
            return;
    }

    const payload = JSON.parse(atob(jwtToken.split('.')[1]));
    const userRole = payload.authorities;

    if (userRole !== 'ADMIN') {
        showCustomAlert('You do not have permission to access this page.',function() {
            window.location.href = '/login.html'; // Redirect to login page
            });
            return;
    }

    // Fetch project list and populate dropdown
    fetch('http://localhost:8080/employee/getAllProjectsForAssign', {
        headers: {
            'Authorization': `Bearer ${jwtToken}`
        }
    })
    .then(response => response.json())
    .then(data => {
        data.forEach(project => {
            const option = document.createElement('option');
            option.value = project.id;
            option.textContent = `${project.id} - ${project.projectName}`;
            projectSelect.appendChild(option);
        });
    })
    .catch(error => console.error('Error fetching projects:', error));

    // Handle assign project button click
    assignProjectBtn.addEventListener('click', function () {
        const selectedProjectId = projectSelect.value;

        if (!selectedProjectId) {
            errorProject.textContent = 'Project is required';
            return;
        }

        errorProject.textContent = '';

        const assignProjectData = {
            id: selectedProjectId,
            empId: empId
        };

        fetch('http://localhost:8080/employee/assignProject', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwtToken}`
            },
            body: JSON.stringify(assignProjectData)
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            showCustomAlert(data.message, function() {
            window.location.href = 'adminDashboard.html'; // Redirect to admin dashboard
        });
        })
        .catch(error => console.error('Error assigning project:', error));
    });

    // Handle cancel button click
    cancelBtn.addEventListener('click', function () {
        window.location.href = 'adminDashboard.html'; // Redirect to admin dashboard
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
