@import java.util.List
@import se.main.springboothtmxbankapplication.adapter.gui.model.CustomerRepresentation
@import se.main.springboothtmxbankapplication.adapter.gui.model.AccountRepresentation

@param List<CustomerRepresentation> customers
@param List<AccountRepresentation> accounts

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Accounts</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css">
    <script src="https://unpkg.com/htmx.org@1.6.1"></script>
</head>
<body>

<h1>Customers</h1>
@template.customers(customers = customers)

<h1 style="margin-top: 50px">Accounts</h1>
@template.accounts(accounts = accounts)

<h2 style="margin-top: 50px">Create account</h2>
<form hx-post="/accounts" hx-target="#result" hx-swap="outerHTML">
    <label for="customerId">Customer ID:</label>
    <input type="text" id="customerId" name="customerId" required><br><br>

    <label for="initialCredit">Initial Credit:</label>
    <input type="number" id="initialCredit" name="initialCredit" required><br><br>

    <button type="submit">Create Account</button>
</form>

</body>
</html>