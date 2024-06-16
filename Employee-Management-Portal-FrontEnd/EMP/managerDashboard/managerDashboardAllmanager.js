document.addEventListener('DOMContentLoaded', function () {
    const jwtToken = localStorage.getItem('jwtToken');

    if (!jwtToken) {
        showCustomAlert('Please login first.',function() {
            window.location.href = '/login.html'; // Redirect to login page
            });
            return;
    }

    // Decode the JWT token to get the payload
    const payload = JSON.parse(atob(jwtToken.split('.')[1]));

    // Extract the user role from the payload
    const userRole = payload.authorities; // Assuming the role is stored in the token
    if (userRole !== 'MANAGER') {
        showCustomAlert('You do not have permission to access this page.',function() {
            window.location.href = '/login.html'; // Redirect to login page
            });
            return;
    }

    // Fetch managers from the server
    fetchManagers();

    function fetchManagers() {
        fetch('http://localhost:8080/employee/getAllManagers', {
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        })
            .then(response => response.json())
            .then(data => {
                displayManagers(data);
            })
            .catch(error => console.error('Error fetching managers:', error));
    }

    // Display managers on the page
    function displayManagers(managers) {
        const mainElement = document.querySelector('.main-ele');

        if (managers.length === 0) {
            mainElement.innerHTML = '<p>No managers available</p>';
            return;
        }

        managers.forEach(manager => {
            const managerCard = createManagerCard(manager);
            mainElement.appendChild(managerCard);
        });
    }

    // Create HTML elements for manager card
    function createManagerCard(manager) {
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
        // leftSection.appendChild(contact);
        leftSection.appendChild(email);

        const rightSection = document.createElement('div');
        rightSection.classList.add('right-section');

        rightSection.appendChild(empId);
        rightSection.appendChild(location);
        rightSection.appendChild(contact);

        cardContent.appendChild(leftSection);
        cardContent.appendChild(rightSection);

        card.appendChild(cardContent);

        fetch(`http://localhost:8080/employee/getAll/project/${manager.id}`, {
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        })
            .then(response => response.json())
            .then(projects => {
                if (projects.length > 0) {
                    const defaultProject = projects[0];
                    const projectContainer = document.createElement('div');
                    projectContainer.classList.add('project-container'); // Corrected class name

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

        return card;
    }

    // Helper function to create field element
    function createField(tagName, classNames, text) {
        const field = document.createElement(tagName);
        field.classList.add(...classNames.split(' ')); // Split classNames and add each to classList
        field.textContent = text;
        return field;
    }

    // Helper function to create project dropdown
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

    // // Add button redirection
    // const reqButton = document.querySelector('.req-btn');
    // reqButton.addEventListener('click', function () {
    //     window.location.href = 'requestResource.html';
    // });

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
        // Clear session data if needed
        localStorage.clear(); // Clear all localStorage items
        window.location.href = '/login.html'; // Redirect to login page
    });
});
