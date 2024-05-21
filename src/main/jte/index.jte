<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accounts</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css">
    <script src="https://unpkg.com/htmx.org@1.6.1"></script>
</head>
<body>
<h1>Accounts</h1>
<form action="/account" method="post">
    <div>
        <label for="customer-id">Customer ID:</label>
        <input id="customer-id" type="text" name="customerId" placeholder="Customer-ID"/>
    </div>

    <div>
        <label for="initial-credit">Initial Credit:</label>
        <input id="initial-credit" type="text" name="initialCredit" placeholder="Initial Credit"/>
    </div>
    <button type="submit">Create account</button>
</form>
</body>
</html>