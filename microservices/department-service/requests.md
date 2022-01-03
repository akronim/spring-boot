curl --location --request POST 'http://localhost:9001/departments/' \
--header 'Content-Type: application/json' \
--data-raw '    {
        "departmentName": "IT",
        "departmentAddress": "Abc 123, Bca",
        "departmentCode": "IT-006"
    }'