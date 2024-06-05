document.addEventListener('DOMContentLoaded', function () {
    const apiUrl = 'http://localhost:8080/requestResource/getAll/requests';
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

    // Fetch and display requests
    fetchRequests();

    function fetchRequests() {
        fetch(apiUrl, {
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        })
            .then(response => response.json())
            .then(data => displayRequests(data))
            .catch(error => {
                console.error('Error fetching requests:', error);
                // alert(`Error fetching requests: ${error.message}`);
            });
    }

    function displayRequests(requests) {
        const tableBody = document.getElementById('request-table-body');
        tableBody.innerHTML = '';

        requests.forEach(request => {
            const row = document.createElement('tr');

            row.innerHTML = `
                <td>${request.employeeName}</td>
                <td>${request.projectName}</td>
                <td>${request.managerName}</td>
                <td>${request.comment}</td>
                <td>
                    <button class="action-button accept-button" onclick="handleAccept(${request.id})">Accept</button>
                    <button class="action-button reject-button" onclick="handleReject(${request.id})">Reject</button>
                </td>
            `;

            tableBody.appendChild(row);
        });
    }

    window.handleAccept = function (id) {
        const acceptUrl = `http://localhost:8080/request/accept/${id}`;
        fetch(acceptUrl, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        })
            .then(response => response.json())
            .then(data => {
                alert(data.message);
                fetchRequests();
            })
            .catch(error => {
                console.error('Error accepting request:', error);
                alert(`Error accepting request`);
            });
    };

    window.handleReject = function (id) {
        const rejectUrl = `http://localhost:8080/requestResource/reject/${id}`;
        fetch(rejectUrl, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        })
            .then(response => response.json())
            .then(data => {
                alert(data.message);
                fetchRequests();
            })
            .catch(error => {
                console.error('Error rejecting request:', error);
                alert(`Error rejecting request`);
            });
    };

    window.goBack = function () {
        window.location.href = 'adminDashboardManager.html';
    };
});
