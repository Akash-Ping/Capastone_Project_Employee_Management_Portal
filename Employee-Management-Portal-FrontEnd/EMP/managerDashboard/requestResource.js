document.addEventListener('DOMContentLoaded', function () {
    const projectSelect = document.getElementById('project-select');
    const commentField = document.getElementById('comment');
    const form = document.getElementById('request-resource-form');
    const cancelBtn = document.getElementById('cancel-btn');

    const jwtToken = localStorage.getItem('jwtToken');
    const selectedEmployeeId = localStorage.getItem('selectedEmployeeId');

    if (!jwtToken || !selectedEmployeeId) {
        alert('Session expired or invalid access.');
        window.location.href = 'managerDashboard.html';
        return;
    }

    // Decode the JWT token to get the payload
    const payload = JSON.parse(atob(jwtToken.split('.')[1]));

    // Extract the email from the payload
    const sessionUserEmail = payload.email;

    localStorage.setItem('userRole', payload.authorities);

    const userRole = payload.authorities; // Assuming the role is stored in the token
    if (userRole !== 'MANAGER') {
        alert('You do not have permission to access this page.');
        window.location.href = '/login.html'; // Redirect to login page
        return;
    }

    fetchProjects(sessionUserEmail);

    cancelBtn.addEventListener('click', function () {
        window.location.href = 'managerDashboard.html';
    });

    form.addEventListener('submit', function (event) {
        event.preventDefault();
        const projectId = projectSelect.value;
        const comment = commentField.value;
        requestResource(selectedEmployeeId, sessionUserEmail, projectId, comment);
    });

    function fetchProjects(email) {
        fetch(`http://localhost:8080/getAll/project/byManager/${email}`, {
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        })
            .then(response => response.json())
            .then(data => {
                displayProjects(data);
            })
            .catch(error => console.error('Error fetching projects:', error));
    }

    function displayProjects(projects) {
        projects.forEach(project => {
            const option = document.createElement('option');
            option.value = project.id;
            option.textContent = project.projectName;
            projectSelect.appendChild(option);
        });
    }

    function requestResource(empId, email, projectId, comment) {
        const requestData = {
            empId: empId,
            email: email,
            projectId: projectId,
            comment: comment,
        };

        fetch('http://localhost:8080/requestResource/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwtToken}`
            },
            body: JSON.stringify(requestData),
        })
            .then(response => response.json())
            .then(data => {
                alert(data.message);
                window.location.href = 'managerDashboard.html';
            })
            .catch(error => console.error('Error requesting resource:', error));
    }
});
