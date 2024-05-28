@import java.util.List
@import se.main.springboothtmxbankapplication.adapter.gui.model.CustomerRepresentation

@param List<CustomerRepresentation> customers

<table border="1">
    <thead>
    <tr>
        <th>Customer ID</th>
        <th>Name</th>
        <th>Surname</th>
    </tr>
    </thead>
    <tbody>
    @for(CustomerRepresentation customer : customers)
        <tr>
            <td style="width: 400px">${customer.customerId()}</td>
            <td style="width: 200px">${customer.name()}</td>
            <td style="width: 200px">${customer.surname()}</td>
        </tr>
    @endfor
    </tbody>
</table>

