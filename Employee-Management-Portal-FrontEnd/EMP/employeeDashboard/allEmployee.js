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

    if (userRole !== 'EMPLOYEE') {
        showCustomAlert('You do not have permission to access this page.',function() {
            window.location.href = '/login.html'; // Redirect to login page
            });
            return;
    }

    fetch('http://localhost:8080/employee/getAllEmployeesAndManagers', {
        headers: {
            'Authorization': `Bearer ${jwtToken}`
        }
    })
    .then(response => response.json())
    .then(data => {
        displayEmployees(data);
    })
    .catch(error => console.error('Error fetching employees:', error));

    function displayEmployees(employees) {
        const mainElement = document.querySelector('.main-ele');

        employees.forEach(employee => {
            const employeeCard = createEmployeeCard(employee);
            mainElement.appendChild(employeeCard);
        });
    }

    function createEmployeeCard(employee) {
        const card = document.createElement('div');
        card.classList.add('card');

        const cardContent = document.createElement('div');
        cardContent.classList.add('card-content');

        // Left section
        const leftSection = document.createElement('div');
        leftSection.classList.add('left-section-allemployees');

        const empId = createField('div', 'emp-id', `Employee ID: ${employee.empId}`);
        const employeeName = createField('div', 'employee-name larger', `Name: ${employee.name}`);
        const designation = createField('div', 'field smaller', `Designation: ${employee.designation}`);
        const manager = createField('div', 'field', `Manager: ${employee.managerName ? employee.managerName : 'N/A'}`);

        leftSection.appendChild(empId);
        leftSection.appendChild(employeeName);
        leftSection.appendChild(designation);
        leftSection.appendChild(manager);

        // Right section
        const rightSection = document.createElement('div');
        rightSection.classList.add('right-section');

        const dob = createField('div', 'field', `DOB: ${employee.dob}`);
        const doj = createField('div', 'field', `DOJ: ${employee.doj}`);
        const location = createField('div', 'field', `Location: ${employee.location}`);
        const contact = createField('div', 'field', `Contact: ${employee.contactNo}`);
        const email = createField('div', 'field', `Email: ${employee.email}`);
        const role = createField('div', 'field', `Role: ${employee.role}`);
        const projectName = createField('div', 'field', `Project Name: ${employee.projectName ? employee.projectName : 'N/A'}`);

        rightSection.appendChild(dob);
        rightSection.appendChild(doj);
        rightSection.appendChild(location);
        rightSection.appendChild(contact);
        leftSection.appendChild(email);
        leftSection.appendChild(role);
        rightSection.appendChild(projectName);

        cardContent.appendChild(leftSection);
        cardContent.appendChild(rightSection);

        card.appendChild(cardContent);

        return card;
    }

    function createField(tagName, classNames, text) {
        const field = document.createElement(tagName);
        field.classList.add(...classNames.split(' ')); // Split classNames and add each to classList
        field.textContent = text;
        return field;
    }

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
