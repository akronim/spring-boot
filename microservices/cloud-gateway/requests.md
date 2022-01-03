curl --location --request POST 'http://localhost:9191/departments/' \
--header 'Content-Type: application/json' \
--data-raw '    {
        "departmentName": "IT",
        "departmentAddress": "Abc 123, Bca",
        "departmentCode": "IT-006"
    }'
    
    
curl --location --request GET 'http://localhost:9191/departments/1' \
--header 'Content-Type: application/json' \
--data-raw '    {
        "departmentName": "IT",
        "departmentAddress": "Abc 123, Bca",
        "departmentCode": "IT-006"
    }'
    


curl --location --request POST 'http://localhost:9191/users/' \
--header 'Content-Type: application/json' \
--data-raw '    {
        "firstName": "John",
        "lastName": "Doe",
        "email": "john@gmail.com",
        "departmentId": "1"
    }'


curl --location --request GET 'http://localhost:9191/users/1' \
--header 'Content-Type: application/json' \
--data-raw '    {
        "firstName": "John",
        "lastName": "Doe",
        "email": "john@gmail.com",
        "departmentId": "1"
    }'








