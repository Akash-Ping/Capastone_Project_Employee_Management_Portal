document.addEventListener('DOMContentLoaded', function () {
    const jwtToken = localStorage.getItem('jwtToken');

    if (!jwtToken) {
        alert('Session expired or invalid access.');
        window.location.href = 'login.html';
        return;
    }

    // Decode the JWT token to get the payload
    const payload = JSON.parse(atob(jwtToken.split('.')[1]));

    // Extract the user role from the payload
    const userRole = payload.authorities; // Assuming the role is stored in the token
    if (userRole !== 'MANAGER') {
        alert('You do not have permission to access this page.');
        window.location.href = '/login.html'; // Redirect to login page
        return;
    }

    fetchProjects();

    function fetchProjects() {
        fetch('http://localhost:8080/employee/getAllProjects', {
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
        const mainElement = document.querySelector('.main-ele');

        projects.forEach(project => {
            const projectCard = createProjectCard(project);
            mainElement.appendChild(projectCard);
        });
    }

    function createProjectCard(project) {
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
    // const addButton = document.querySelector('.add-btn');
    // addButton.addEventListener('click', function () {
    //     window.location.href = 'addProject.html';
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
