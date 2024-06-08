document.addEventListener('DOMContentLoaded', function () {
    const skillSearchInput = document.getElementById('skill-search');
    const skillDropdown = document.getElementById('skill-dropdown');
    const selectedSkills = [];

    const jwtToken = localStorage.getItem('jwtToken');

    if (!jwtToken) {
        alert('Please login first.');
        window.location.href = '/login.html'; // Redirect to login page
        return;
    }

    // Decode the JWT token to get the payload
    const payload = JSON.parse(atob(jwtToken.split('.')[1]));
    const userRole = payload.authorities;

    if (userRole !== 'MANAGER') {
        alert('You do not have permission to access this page.');
        window.location.href = '/login.html'; // Redirect to login page
        return;
    }

    // Extract the email from the payload
    const userEmail = payload.email;

    // Clear search bar and checkbox on page load
    skillSearchInput.value = '';
    document.getElementById('show-unassigned').checked = false;

    fetchSkills();
    fetchFilteredEmployees([], false);

    skillSearchInput.addEventListener('click', function () {
        skillDropdown.style.display = 'block';
    });

    skillDropdown.addEventListener('click', function (event) {
        const skill = event.target.textContent;

        // Check if skill is already selected
        const skillIndex = selectedSkills.indexOf(skill);
        if (skillIndex !== -1) {
            // Skill is already selected, so remove it
            selectedSkills.splice(skillIndex, 1);
        } else {
            // Skill is not selected, so add it
            selectedSkills.push(skill);
        }

        skillSearchInput.value = selectedSkills.join(', ');
    });

    // Close dropdown when clicking outside
    document.addEventListener('click', function (event) {
        if (!skillSearchInput.contains(event.target) && !skillDropdown.contains(event.target)) {
            closeDropdown();
        }
    });

    document.querySelector('.search-btn').addEventListener('click', function () {
        const showUnassignedOnly = document.getElementById('show-unassigned').checked;
        fetchFilteredEmployees(selectedSkills, showUnassignedOnly);
    });

    function fetchSkills() {
        fetch('http://localhost:8080/employee/allSkills', {
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        })
        .then(response => response.json())
        .then(data => displaySkillsDropdown(skillDropdown, data.map(skill => skill.skillName)))
        .catch(error => console.error('Error fetching skills:', error));
    }

    function displaySkillsDropdown(dropdown, skills) {
        dropdown.innerHTML = '';
        skills.forEach(skill => {
            const skillElement = document.createElement('div');
            skillElement.textContent = skill;
            dropdown.appendChild(skillElement);
        });
    }

    function closeDropdown() {
        skillDropdown.style.display = 'none';
    }

    function fetchFilteredEmployees(skills, showUnassignedOnly) {
        const filterData = {
            skills: skills,
            checked: showUnassignedOnly,
        };

        fetch('http://localhost:8080/employee/filter', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwtToken}`
            },
            body: JSON.stringify(filterData)
        })
        .then(response => response.json())
        .then(data => {
            displayEmployees(data);
            checkResourceRequests(data);
        })
        .catch(error => console.error('Error fetching filtered employees:', error));
    }

    function displayEmployees(employees) {
        const mainElement = document.querySelector('.main-ele');
        mainElement.innerHTML = '';
        employees.forEach(employee => {
            const employeeCard = createEmployeeCard(employee);
            mainElement.appendChild(employeeCard);
        });
    }

    function checkResourceRequests(employees) {
        employees.forEach(employee => {
            isRequested(employee.empId, (requested) => {
                const requestButton = document.querySelector(`[data-empid="${employee.empId}"]`);
                if (requestButton && requested) {
                    requestButton.textContent = 'Requested';
                    requestButton.classList.add('requested'); // Add a class for styling
                    requestButton.disabled = true;
                }
            });
        });
    }

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
        const managerName = createField('div', 'field', `Manager Name: ${employee.managerName ? employee.managerName : 'N/A'}`);
        const contact = createField('div', 'field', `Contact: ${employee.contactNo}`);
        const email = createField('div', 'field', `Email: ${employee.email}`);
        const empId = createField('div', 'emp-id', `Employee ID: ${employee.empId}`);
        const dob = createField('div', 'field', `DOB: ${employee.dob}`);
        const doj = createField('div', 'field', `DOJ: ${employee.doj}`);
        const location = createField('div', 'field', `Location: ${employee.location}`);
        const skills = createField('div', 'field', `Skills: ${employee.assignedSkills.join(', ')}`);

        leftSection.appendChild(employeeName);
        leftSection.appendChild(designation);
        leftSection.appendChild(projectName);
        leftSection.appendChild(managerName);
        leftSection.appendChild(contact);
        leftSection.appendChild(email);

        const rightSection = document.createElement('div');
        rightSection.classList.add('right-section');

        rightSection.appendChild(empId);
        rightSection.appendChild(dob);
        rightSection.appendChild(doj);
        rightSection.appendChild(location);
        rightSection.appendChild(skills);

        cardContent.appendChild(leftSection);
        cardContent.appendChild(rightSection);

        card.appendChild(cardContent);

        // Add the "Request" button if the employee is not assigned to a project
        if (employee.projectId === null) {
            const requestButton = document.createElement('button');
            requestButton.textContent = 'Request';
            requestButton.classList.add('request-btn');
            requestButton.dataset.empid = employee.empId; // Store empId as a data attribute
            requestButton.addEventListener('click', function () {
                // Store employee details in localStorage
                localStorage.setItem('selectedEmployeeId', employee.empId);
                // Redirect to the request resource page
                window.location.href = 'requestResource.html';
            });
            card.appendChild(requestButton);
        }

        return card;
    }

    function isRequested(empId, callback) {
        const data = { empId: empId, email: userEmail };
        fetch('http://localhost:8080/requestResource/isRequested', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwtToken}`
            },
            body: JSON.stringify(data)
        })
        .then(response => response.json())
        .then(data => {
            callback(data.requested);
        })
        .catch(error => console.error('Error checking if resource is requested:', error));
    }

    function createField(tagName, classNames, text) {
        const field = document.createElement(tagName);
        field.classList.add(...classNames.split(' '));
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
        // Clear session data if needed
        localStorage.clear(); // Clear all localStorage items
        window.location.href = '/login.html'; // Redirect to login page
    });
});
