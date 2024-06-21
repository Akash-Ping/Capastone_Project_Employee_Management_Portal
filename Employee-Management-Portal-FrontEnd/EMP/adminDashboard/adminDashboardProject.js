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

    fetchProjects();

    function getJwtToken() {
        const token = localStorage.getItem('jwtToken');
        console.log('JWT Token:', token);
        return token;
    }

    function fetchProjects() {
        const jwtToken = getJwtToken();
        if (!jwtToken) {
            showCustomAlert('Please login first.',function() {
                window.location.href = '/login.html'; // Redirect to login page
                });
                return;
        }

        console.log('Fetching projects with JWT token...');
        fetch('http://localhost:8080/employee/getAllProjects', {
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        })
            .then(response => {
                console.log('Fetch response:', response);
                return response.json();
            })
            .then(data => {
                console.log('Projects data:', data);
                displayProjects(data);
            })
            .catch(error => console.error('Error fetching projects:', error));
    }

    function displayProjects(projects) {
        const mainElement = document.querySelector('.main-ele');
        console.log('Displaying projects:', projects);

        projects.forEach(project => {
            const projectCard = createProjectCard(project);
            console.log('Appending project card:', projectCard);
            mainElement.appendChild(projectCard);
        });
    }

    function createProjectCard(project) {
        console.log('Creating card for project:', project);
        const card = document.createElement('div');
        card.classList.add('card');

        const cardContent = document.createElement('div');
        cardContent.classList.add('card-content');

        const leftSection = document.createElement('div');
        leftSection.classList.add('left-section-projects');

        const projectName = createField('div', 'project-name', project.projectName);
        const head = createField('div', 'field', `Head: ${project.head}`);
        const description = createField('div', 'field', `Description: ${project.description}`);
        const teamMembers = createField('div', 'field', `Team Members: ${project.teamMembers.join(', ') || 'N/A'}`);

        leftSection.appendChild(projectName);
        leftSection.appendChild(head);
        leftSection.appendChild(description);
        leftSection.appendChild(teamMembers);

        const rightSection = document.createElement('div');
        rightSection.classList.add('right-section-projects');

        const projectId = createField('div', 'field', `Project ID: ${project.id}`);
        const startDate = createField('div', 'field', `Start Date: ${project.startDate}`);

        rightSection.appendChild(projectId);
        rightSection.appendChild(startDate);

        cardContent.appendChild(leftSection);
        cardContent.appendChild(rightSection);

        card.appendChild(cardContent);

        return card;
    }

    function createField(tagName, classNames, text) {
        const field = document.createElement(tagName);
        field.classList.add(...classNames.split(' '));
        field.textContent = text;
        return field;
    }

    // Add button redirection
    const addButton = document.querySelector('.add-btn');
    addButton.addEventListener('click', function () {
        window.location.href = 'addProject.html';
    });

        // Assign Manager button redirection
        const assignButton = document.querySelector('.assign-btn');
        assignButton.addEventListener('click', function () {
            window.location.href = 'assignManager.html';
        });

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
