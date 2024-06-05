document.addEventListener('DOMContentLoaded', function () {
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

    // Fetch employees from the server
    fetchEmployees();

    function fetchEmployees() {
        fetch('http://localhost:8080/employee/getAllEmployees', {
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        })
        .then(response => response.json())
        .then(data => {
            displayEmployees(data);
        })
        .catch(error => console.error('Error fetching employees:', error));
    }

    // Display employees on the page
    function displayEmployees(employees) {
        const mainElement = document.querySelector('.main-ele');
        mainElement.innerHTML = ''; // Clear existing employees

        employees.forEach(employee => {
            const employeeCard = createEmployeeCard(employee);
            mainElement.appendChild(employeeCard);
        });
    }

    // Create HTML elements for employee card
    function createEmployeeCard(employee) {
        const card = document.createElement('div');
        card.classList.add('card');

        const cardContent = document.createElement('div');
        cardContent.classList.add('card-content');

        const leftSection = document.createElement('div');
        leftSection.classList.add('left-section-allemployees');

        const employeeName = createField('div', 'employee-name larger', employee.name);
        const designation = createField('div', 'field smaller', employee.designation);
        const projectName = createField('div', 'field', `Project Name: ${employee.projectName ? employee.projectName : 'N/A'}`);
        const manager = createField('div', 'field', `Manager: ${employee.managerName ? employee.managerName : 'N/A'}`);
        const contact = createField('div', 'field', `Contact: ${employee.contactNo}`);
        const email = createField('div', 'field', `Email: ${employee.email}`);

        leftSection.appendChild(employeeName);
        leftSection.appendChild(designation);
        leftSection.appendChild(projectName);
        leftSection.appendChild(manager);
        leftSection.appendChild(contact);
        leftSection.appendChild(email);

        const rightSection = document.createElement('div');
        rightSection.classList.add('right-section');

        const empId = createField('div', 'emp-id', `Employee ID: ${employee.empId}`);
        const dob = createField('div', 'field', `DOB: ${employee.dob}`);
        const doj = createField('div', 'field', `DOJ: ${employee.doj}`);
        const location = createField('div', 'field', `Location: ${employee.location}`);

        rightSection.appendChild(empId);
        rightSection.appendChild(dob);
        rightSection.appendChild(doj);
        rightSection.appendChild(location);

        cardContent.appendChild(leftSection);
        cardContent.appendChild(rightSection);

        const buttonsSection = document.createElement('div');
        buttonsSection.classList.add('card-buttons');

        const updateButton = createButton('Update', 'update');
        const deleteButton = createButton('Delete', 'delete');
        const assignButtonText = employee.projectName ? 'Unassign' : 'Assign';
        const assignButton = createButton(assignButtonText, 'assign');

        assignButton.addEventListener('click', function() {
            if (assignButton.textContent === 'Unassign') {
                unassignProject(employee.empId);
            } else {
                // Handle assigning project logic here if needed
                localStorage.setItem('empId', employee.empId);
                window.location.href = 'assignProject.html'; // Redirect to assign project page
            }
        });

        updateButton.addEventListener('click', function() {
            // Save employee data to localStorage
            localStorage.setItem('employeeToUpdate', JSON.stringify(employee));
            window.location.href = 'updateEmployee.html'; // Redirect to update employee page
        });

        deleteButton.addEventListener('click', function() {
            deleteEmployee(employee.empId, card);
        });

        buttonsSection.appendChild(updateButton);
        buttonsSection.appendChild(deleteButton);
        buttonsSection.appendChild(assignButton);

        card.appendChild(cardContent);
        card.appendChild(buttonsSection);

        return card;
    }

    // Helper function to create field element
    function createField(tagName, classNames, text) {
        const field = document.createElement(tagName);
        field.classList.add(...classNames.split(' ')); // Split classNames and add each to classList
        field.textContent = text;
        return field;
    }

    // Helper function to create button element
    function createButton(text, className) {
        const button = document.createElement('button');
        button.classList.add(className);
        button.textContent = text;
        return button;
    }

    // Add button redirection
    const addButton = document.querySelector('.add-btn');
    addButton.addEventListener('click', function () {
        window.location.href = 'addEmployee.html';
    });

    // Delete employee function
    function deleteEmployee(empId, cardElement) {
        fetch(`http://localhost:8080/employee/delete/${empId}`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        })
        .then(response => {
            if (response.ok) {
                cardElement.remove(); // Remove card from the DOM
            } else {
                console.error('Failed to delete employee');
            }
        })
        .catch(error => console.error('Error:', error));
    }

    // Unassign project function
    function unassignProject(empId) {
        fetch(`http://localhost:8080/employee/unassignProject/${empId}`, {
            method: 'PUT',
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        })
        .then(response => {
            if (response.ok) {
                fetchEmployees(); // Refresh employee list
            } else {
                console.error('Failed to unassign project');
            }
        })
        .catch(error => console.error('Error:', error));
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
});
