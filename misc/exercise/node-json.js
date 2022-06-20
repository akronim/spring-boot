const fs = require("fs");
const crypto = require('crypto');

function jsonReader(filePath, cb) {
    fs.readFile(filePath, (err, fileData) => {
        if (err) {
            return cb && cb(err);
        }

        try {

            let employeesNew = [];

            const employees = JSON.parse(fileData);

            employees.forEach(employee => {
                let projects = [];

                employee.projects.forEach(p => {
                    projects.push({
                        _id: crypto.randomUUID(),
                        title: p,
                        description: p + " - Lorem ipsum dolor sit amet."
                    });
                })

                employee.projects_2 = projects;
                employeesNew.push(employee);
            });

            return cb && cb(null, employeesNew);

        } catch (err) {
            return cb && cb(err);
        }
    });
}

let filePath = "./employees.json";

jsonReader(filePath, (err, employeesNew) => {
    if (err) {
        console.log(err);
        return;
    }

    fs.writeFile(filePath, JSON.stringify(employeesNew), err => {
        if (err) {
            console.log('Error writing file', err)
        } else {
            console.log('Successfully wrote file')
        }
    })
});

