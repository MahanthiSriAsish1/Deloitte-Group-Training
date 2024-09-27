const BASE_URL = 'http://localhost:5010/EmployeeManagementSystem/api';

let departments = [];
let employees = [];

async function fetchDepartments() {
    try {
        const response = await fetch(`${BASE_URL}/departments`);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        departments = await response.json();
        updateDepartmentDropdown();
    } catch (error) {
        console.error('Error fetching departments:', error);
    }
}

async function fetchEmployees() {
    try {
        const response = await fetch(`${BASE_URL}/employees`);
        (response);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        employees = await response.json();
        console.log(employees);
        
        displayEmployees();
    } catch (error) {
        console.error('Error fetching employees:', error);
    }
}

async function addEmployee() {
    const employeeName = document.getElementById('employeeName').value;
    const employeeSalary = parseFloat(document.getElementById('employeeSalary').value);
    const departmentId = document.getElementById('departmentSelect').value;

    const employeeData = {
        name: employeeName,
        salary: employeeSalary,
        departmentId: departmentId
    };

    console.log(JSON.stringify(employeeData));
    

    try {
        const response = await fetch(`${BASE_URL}/employees`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(employeeData)
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        alert('Employee added successfully!');

        document.getElementById('employeeForm').reset();
        fetchEmployees();
    } catch (error) {
        console.error('Error adding employee:', error);
    }
}
async function addDepartment() {
    // Get the input values from the form
    const departmentName = document.getElementById('departmentName').value;
    const departmentLocation = document.getElementById('departmentLocation').value;

    // Create the department object
    const newDepartment = {
        name: departmentName,
        location: departmentLocation
    };

    try {
        // Send POST request to the backend API
        const response = await fetch('http://localhost:5010/EmployeeManagementSystem/api/departments', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(newDepartment) // Convert the object to JSON string
        });

        // Check if the response is okay (status in the range 200-299)
        if (!response.ok) {
            throw new Error('Network response was not ok: ' + response.statusText);
        }

        // Parse the JSON response
        const data = await response.json();

        // Handle successful addition (e.g., display a success message, clear the form)
        console.log('Department added successfully:', data);
        alert('Department added successfully!');

        // Optionally, clear the form
        document.getElementById('departmentForm').reset();

    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
        alert('Error adding department: ' + error.message);
    }
}


function updateDepartmentDropdown() {
    const departmentSelect = document.getElementById('departmentSelect');
    departmentSelect.innerHTML = '';

    departments.forEach(department => {
        const option = document.createElement('option');
        option.value = department.id;
        option.textContent = department.name;
        departmentSelect.appendChild(option);
    });
}

document.getElementById('departmentForm').addEventListener('submit', async function (event) {
    event.preventDefault();

    const departmentName = document.getElementById('departmentName').value;
    const departmentLocation = document.getElementById('departmentLocation').value;

    const departmentData = {
        name: departmentName,
        location: departmentLocation
    };

    try {
        const response = await fetch(`${BASE_URL}/departments`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(departmentData)
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        await fetchDepartments();
        this.reset();
    } catch (error) {
        console.error('Error adding department:', error);
    }
});

function displayEmployees() {
    const employeesTableBody = document.querySelector('#employeesTable tbody');
    employeesTableBody.innerHTML = '';

    employees.forEach(employee => {
        const department = departments.find(dep => dep.id === employee.departmentId);
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${employee.id}</td>
            <td>${employee.name}</td>
            <td>${employee.salary.toFixed(2)}</td>
            <td>${department ? department.name : 'N/A'}</td>
        `;
        employeesTableBody.appendChild(row);
    });
}

window.onload = function () {
    init();
};

async function init() {
    await fetchDepartments();
    fetchEmployees();
}
