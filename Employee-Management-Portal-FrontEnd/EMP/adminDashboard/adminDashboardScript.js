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
            // Sort employees by id
            data.sort((a, b) => a.id - b.id);
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
            openDeleteConfirmationModal(employee.empId, card);
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

    // Create and append the modal to the body
    function createDeleteConfirmationModal() {
        const modal = document.createElement('div');
        modal.id = 'deleteConfirmationModal';
        modal.classList.add('modal');
        modal.innerHTML = `
            <div class="modal-content">
                <p>Do you want to delete this employee?</p>
                <button id="confirmDeleteButton">Yes</button>
                <button id="cancelDeleteButton">No</button>
            </div>
        `;
        document.body.appendChild(modal);

        // Add event listeners for modal buttons
        const confirmDeleteButton = modal.querySelector('#confirmDeleteButton');
        const cancelDeleteButton = modal.querySelector('#cancelDeleteButton');

        cancelDeleteButton.addEventListener('click', () => modal.style.display = 'none');
        window.addEventListener('click', (event) => {
            if (event.target == modal) {
                modal.style.display = 'none';
            }
        });

        return { modal, confirmDeleteButton };
    }

    const { modal: deleteConfirmationModal, confirmDeleteButton } = createDeleteConfirmationModal();

    // Open the modal
    function openDeleteConfirmationModal(empId, cardElement) {
        deleteConfirmationModal.style.display = 'block';

        confirmDeleteButton.onclick = function() {
            deleteEmployee(empId, cardElement);
            deleteConfirmationModal.style.display = 'none';
        };
    }

    // Delete employee
    function deleteEmployee(empId, cardElement) {
        fetch(`http://localhost:8080/employee/delete/${empId}`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        })
        .then(response => {
            if (response.ok) {
                cardElement.remove(); // Remove the employee card from the DOM
            } else {
                console.error('Error deleting employee:', response.statusText);
            }
        })
        .catch(error => console.error('Error deleting employee:', error));
    }

    // Unassign project from employee
    function unassignProject(empId) {
        fetch(`http://localhost:8080/employee/unassignProject/${empId}`, {
            method: 'PUT',
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        })
        .then(response => {
            if (response.ok) {
                fetchEmployees(); // Refresh the employee list
            } else {
                console.error('Error unassigning project:', response.statusText);
            }
        })
        .catch(error => console.error('Error unassigning project:', error));
    }

    // Logout button
    const logoutBtn = document.querySelector('.logout-btn');
    logoutBtn.addEventListener('click', function() {
        localStorage.removeItem('jwtToken'); // Remove token from localStorage
        window.location.href = '/login.html'; // Redirect to login page
    });

    // Sidebar navigation
    const sidebarItems = document.querySelectorAll('aside.sidebar-ele ul li');
    sidebarItems.forEach(item => {
        item.addEventListener('click', function() {
            const page = item.getAttribute('data-page');
            window.location.href = page;
        });
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
