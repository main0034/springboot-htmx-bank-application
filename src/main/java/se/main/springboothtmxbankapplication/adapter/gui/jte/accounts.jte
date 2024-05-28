@import java.util.List
@import se.main.springboothtmxbankapplication.adapter.gui.model.AccountRepresentation

@param List<AccountRepresentation> accounts

<table border="1">
    <thead>
    <tr>
        <th>Account ID</th>
        <th>Customer ID</th>
        <th>Balance</th>
    </tr>
    </thead>
    <tbody>
    @for(AccountRepresentation account : accounts) {
        <tr>
            <td style="width: 400px">>${account.accountId()}</td>
            <td style="width: 400px">>${account.customerId()}</td>
            <td style="width: 100px">>${account.balance()}</td>
        </tr>
    @endfor
    </tbody>
</table>