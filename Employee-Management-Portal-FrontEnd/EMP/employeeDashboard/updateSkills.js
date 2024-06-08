document.addEventListener('DOMContentLoaded', function() {
    const jwtToken = localStorage.getItem('jwtToken');

    if (!jwtToken) {
        alert('Session expired or invalid access.');
        window.location.href = 'login.html';
        return;
    }

    // const empId = localStorage.getItem('empId');
    const currentSkillsList = document.getElementById('current-skills-list');
    const skillSelect = document.getElementById('skill-select');
    const errorMessage = document.getElementById('error-message');
    const addSkillButton = document.getElementById('add-skill-button');
    const updateButton = document.getElementById('update-button');
    const closeButton = document.getElementById('close-button');

    let currentSkills = [];
    let allSkills = [];

    // Decode the JWT token to get the payload
    const payload = JSON.parse(atob(jwtToken.split('.')[1]));

    // Extract the user role from the payload
    const userRole = payload.authorities; // Assuming the role is stored in the token
    if (userRole !== 'EMPLOYEE') {
        alert('You do not have permission to access this page.');
        window.location.href = '/login.html'; // Redirect to login page
        return;
    }

    const userEmail = payload.email;

    if (!userEmail) {
        alert('User email not found.');
        return;
    }

    // if (!empId) {
    //     alert('Employee ID not found.');
    //     return;
    // }

    // Fetch employee's assigned skills
    fetch(`http://localhost:8080/employee/Employees/${userEmail}`, {
        headers: {
            'Authorization': `Bearer ${jwtToken}`
        }
    })
    .then(response => response.json())
    .then(data => {
        currentSkills = data.assignedSkills.map(skill => skill.skillName);
        renderCurrentSkills();
        fetchAllSkills();
    })
    .catch(error => console.error('Error fetching employee skills:', error));

    // Fetch all available skills
    function fetchAllSkills() {
        fetch('http://localhost:8080/employee/allSkills', {
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        })
        .then(response => response.json())
        .then(data => {
            allSkills = data;
            renderSkillsSelect();
        })
        .catch(error => console.error('Error fetching all skills:', error));
    }

    // Render current skills list
    function renderCurrentSkills() {
        currentSkillsList.innerHTML = '';
        currentSkills.forEach(skill => {
            const skillItem = document.createElement('div');
            skillItem.className = 'current-skill-item';
            skillItem.textContent = skill;

            const removeButton = document.createElement('button');
            removeButton.className = 'remove-skill-button';
            removeButton.textContent = 'Remove';
            removeButton.addEventListener('click', () => handleRemoveSkill(skill));

            skillItem.appendChild(removeButton);
            currentSkillsList.appendChild(skillItem);
        });
    }

    // Render skills select options
    function renderSkillsSelect() {
        skillSelect.innerHTML = '<option value="">Select a skill</option>';
        allSkills.forEach(skill => {
            if (!currentSkills.includes(skill.skillName)) {
                const option = document.createElement('option');
                option.value = skill.skillName;
                option.textContent = skill.skillName;
                skillSelect.appendChild(option);
            }
        });
    }

    // Handle adding a skill
    addSkillButton.addEventListener('click', () => {
        const selectedSkill = skillSelect.value;
        if (selectedSkill) {
            currentSkills.push(selectedSkill);
            renderCurrentSkills();
            renderSkillsSelect();
            skillSelect.value = '';
            errorMessage.textContent = '';
        } else {
            errorMessage.textContent = 'Select at least one skill';
        }
    });

    // Handle removing a skill
    function handleRemoveSkill(skillToRemove) {
        currentSkills = currentSkills.filter(skill => skill !== skillToRemove);
        renderCurrentSkills();
        renderSkillsSelect();
    }

    // Handle updating skills
    updateButton.addEventListener('click', () => {
        const payload = {
            skillIdsToAdd: allSkills.filter(skill => currentSkills.includes(skill.skillName)).map(skill => skill.id),
            skillIdsToRemove: allSkills.filter(skill => !currentSkills.includes(skill.skillName)).map(skill => skill.id)
        };

        fetch(`http://localhost:8080/api/employee/${userEmail}/updateskills`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwtToken}`
            },
            body: JSON.stringify(payload)
        })
        .then(response => response.json())
        .then(data => {
            alert('Skills updated successfully');
            window.location.href = 'employeeDashboard.html';
        })
        .catch(error => console.error('Error updating skills:', error));
    });

    // Handle close button click
    closeButton.addEventListener('click', () => {
        window.location.href = 'employeeDashboard.html';
    });
});
