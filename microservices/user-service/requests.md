curl --location --request POST 'http://localhost:9002/users/' \
--header 'Content-Type: application/json' \
--data-raw '    {
        "firstName": "John",
        "lastName": "Doe",
        "email": "john@gmail.com",
        "departmentId": "1"
    }'


curl --location --request GET 'http://localhost:9002/users/1' \
--header 'Content-Type: application/json' \
--data-raw '    {
        "firstName": "John",
        "lastName": "Doe",
        "email": "john@gmail.com",
        "departmentId": "1"
    }'