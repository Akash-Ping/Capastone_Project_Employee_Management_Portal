document.addEventListener('DOMContentLoaded', function () {
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

    // Fetch managers from the server
    fetchManagers();

    function getJwtToken() {
        const token = localStorage.getItem('jwtToken');
        console.log('JWT Token:', token);
        return token;
    }

    function fetchManagers() {
        const jwtToken = getJwtToken();
        if (!jwtToken) {
            showCustomAlert('Please login first.',function() {
            window.location.href = '/login.html'; // Redirect to login page
            });
            return;
        }

        console.log('Fetching managers with JWT token...');
        fetch('http://localhost:8080/employee/getAllManagers', {
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        })
            .then(response => {
                console.log('Fetch response:', response);
                return response.json();
            })
            .then(data => {
                console.log('Managers data:', data);
                displayManagers(data);
            })
            .catch(error => console.error('Error fetching managers:', error));
    }

    function displayManagers(managers) {
        const mainElement = document.querySelector('.main-ele');
        console.log('Displaying managers:', managers);

        if (managers.length === 0) {
            mainElement.innerHTML = '<p>No managers available</p>';
            return;
        }

        managers.forEach(manager => {
            const managerCard = createManagerCard(manager);
            console.log('Appending manager card:', managerCard);
            mainElement.appendChild(managerCard);
        });
    }

    function createManagerCard(manager) {
        console.log('Creating card for manager:', manager);
        const card = document.createElement('div');
        card.classList.add('card');

        const cardContent = document.createElement('div');
        cardContent.classList.add('card-content');

        const leftSection = document.createElement('div');
        leftSection.classList.add('left-section-allmanagers');

        const employeeName = createField('div', 'employee-name larger', manager.name);
        const designation = createField('div', 'field smaller', manager.designation);
        const contact = createField('div', 'field', `Contact: ${manager.contactNo}`);
        const email = createField('div', 'field', `Email: ${manager.email}`);
        const empId = createField('div', 'emp-id', `Employee ID: ${manager.empId}`);
        const location = createField('div', 'field', `Location: ${manager.location}`);

        leftSection.appendChild(employeeName);
        leftSection.appendChild(designation);
        leftSection.appendChild(email);

        const rightSection = document.createElement('div');
        rightSection.classList.add('right-section');

        rightSection.appendChild(empId);
        rightSection.appendChild(location);
        rightSection.appendChild(contact);

        cardContent.appendChild(leftSection);
        cardContent.appendChild(rightSection);

        const jwtToken = getJwtToken();
        console.log(`Fetching projects for manager ID ${manager.id} with JWT token...`);
        fetch(`http://localhost:8080/employee/getAll/project/${manager.id}`, {
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        })
            .then(response => {
                console.log(`Fetch projects response for manager ID ${manager.id}:`, response);
                return response.json();
            })
            .then(projects => {
                console.log(`Projects for manager ID ${manager.id}:`, projects);
                if (projects.length > 0) {
                    const defaultProject = projects[0];
                    const projectContainer = document.createElement('div');
                    projectContainer.classList.add('project-container');

                    const projectField = createField('div', 'field', 'Project:');
                    const projectDropdown = createProjectDropdown(projects, defaultProject.id);

                    projectContainer.appendChild(projectField);
                    projectContainer.appendChild(projectDropdown);

                    const startDateField = createField('div', 'field', `Start Date: ${defaultProject.startDate}`);
                    const teamField = createField('div', 'field', `Team: ${defaultProject.teamMembers.join(', ')}`);

                    projectDropdown.addEventListener('change', function () {
                        const selectedProjectId = projectDropdown.value;
                        const selectedProject = projects.find(project => project.id == selectedProjectId);
                        startDateField.textContent = `Start Date: ${selectedProject.startDate}`;
                        teamField.textContent = `Team: ${selectedProject.teamMembers.join(', ')}`;
                    });

                    leftSection.appendChild(projectContainer);
                    leftSection.appendChild(startDateField);
                    leftSection.appendChild(teamField);
                } else {
                    const projectField = createField('div', 'field', `Project: None`);
                    leftSection.appendChild(projectField);
                }
            })
            .catch(error => console.error('Error fetching projects:', error));

        card.appendChild(cardContent);
        return card;
    }

    function createField(tagName, classNames, text) {
        const field = document.createElement(tagName);
        field.classList.add(...classNames.split(' '));
        field.textContent = text;
        return field;
    }

    function createProjectDropdown(projects, defaultProjectId) {
        const select = document.createElement('select');
        projects.forEach(project => {
            const option = document.createElement('option');
            option.value = project.id;
            option.textContent = project.projectName;
            if (project.id === defaultProjectId) {
                option.selected = true;
            }
            select.appendChild(option);
        });
        return select;
    }

    const reqButton = document.querySelector('.req-btn');
    reqButton.addEventListener('click', function () {
        window.location.href = 'requestResource.html';
    });

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
