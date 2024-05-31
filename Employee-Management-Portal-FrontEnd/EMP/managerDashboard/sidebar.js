document.addEventListener('DOMContentLoaded', function () {
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
        // localStorage.removeItem('sessionUserRole');
        // localStorage.removeItem('empId');
        // localStorage.removeItem('employeeToUpdate');
        // localStorage.removeItem('sessionUserRole');
        // localStorage.removeItem('sessionUserName');
        // localStorage.removeItem('sessionUserEmail');
        // localStorage.removeItem('isLoggedIn');
        localStorage.clear(); // Clear all localStorage items
        window.location.href = '/login.html'; // Redirect to login page
    });
});
