document.addEventListener('DOMContentLoaded', function () {
    const projectForm = document.getElementById('add-project-form');
    const managerSelect = document.getElementById('project-manager');
    const cancelButton = document.getElementById('cancel-button');

    const projectNameError = document.getElementById('project-name-error');
    const managerError = document.getElementById('manager-error');
    const startDateError = document.getElementById('start-date-error');
    const descriptionError = document.getElementById('description-error');

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

    fetchManagers();

    projectForm.addEventListener('submit', function (event) {
        event.preventDefault();
        addProject();
    });

    cancelButton.addEventListener('click', function () {
        window.location.href = 'adminDashboardProject.html';
    });

    function fetchManagers() {
        fetch('http://localhost:8080/employee/getAllManagersInfo', {
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        })
            .then(response => response.json())
            .then(data => {
                populateManagerDropdown(data);
            })
            .catch(error => console.error('Error fetching managers:', error));
    }

    function populateManagerDropdown(managers) {
        managers.forEach(manager => {
            const option = document.createElement('option');
            option.value = manager.id;
            option.textContent = `${manager.managerEmployeeId} - ${manager.managerName}`;
            managerSelect.appendChild(option);
        });
    }

    function addProject() {
        const projectName = projectForm.projectName.value.trim();
        const managerId = projectForm.manager.value;
        const startDate = projectForm.startDate.value.trim();
        const description = projectForm.description.value.trim();

        let valid = true;

        if (!projectName) {
            projectNameError.textContent = 'Project name is required';
            valid = false;
        } else {
            projectNameError.textContent = '';
        }

        if (!managerId) {
            managerError.textContent = 'Manager is required';
            valid = false;
        } else {
            managerError.textContent = '';
        }

        if (!startDate) {
            startDateError.textContent = 'Start date is required';
            valid = false;
        } else {
            startDateError.textContent = '';
        }

        if (!description) {
            descriptionError.textContent = 'Description is required';
            valid = false;
        } else {
            descriptionError.textContent = '';
        }

        if (valid) {
            const projectData = {
                projectName,
                description,
                startDate,
                manager: managerId
            };

            fetch('http://localhost:8080/employee/addProject', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${jwtToken}`
                },
                body: JSON.stringify(projectData)
            })
                .then(response => response.json())
                .then(data => {
                    console.log('Response:', data); // Log the response to inspect it
                    if (data.message === 'Project added successfully') {
                        alert('Project added successfully');
                        window.location.href = 'adminDashboardProject.html'; // Redirect to admin dashboard
                    } else {
                        alert(data.message || 'Failed to add project');
                    }
                })
                .catch(error => {
                    console.error('Error adding project:', error);
                    alert('Failed to add project due to an error');
                });
        }
    }
});
