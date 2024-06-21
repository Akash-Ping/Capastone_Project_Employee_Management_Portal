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
        showCustomAlert('Please login first.', function() {
            window.location.href = '/login.html';
        });
        return;
    }

    const payload = JSON.parse(atob(jwtToken.split('.')[1]));
    const userRole = payload.authorities;

    if (userRole !== 'ADMIN') {
        showCustomAlert('You do not have permission to access this page.', function() {
            window.location.href = '/login.html';
        });
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

        // if (!managerId) {
        //     managerError.textContent = 'Manager is required';
        //     valid = false;
        // } else {
        //     managerError.textContent = '';
        // }

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
                if (data.message === 'Project added successfully') {
                    showCustomAlert('Project added successfully', function() {
                        window.location.href = 'adminDashboardProject.html';
                    });
                } else {
                    showCustomAlert(data.message || 'Failed to add project');
                }
            })
            .catch(error => {
                console.error('Error adding project:', error);
                showCustomAlert('Failed to add project due to an error');
            });
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
});
