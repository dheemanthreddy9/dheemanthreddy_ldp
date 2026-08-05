import Typography from "../../atoms/Typography/Typography";

const ContractTable = () => {
  return (
    <div className="contract-table">

      <div className="table-header">

        <Typography
          text="Your Funding"
          variant="subtitle"
        />

        <div className="tabs">
  <button className="tab active-tab">
    My Contracts
  </button>

  <button className="tab">
    My Cash Kicks
  </button>
</div>

      </div>

      <table>

        <thead>

          <tr>
            <th>Name</th>
            <th>Type</th>
            <th>Per Payment</th>
            <th>Term Length</th>
            <th>Payment Remaining</th>
          </tr>

        </thead>

        <tbody>

          <tr>
            <td>Contract 1</td>
            <td>Monthly</td>
            <td>$12,000</td>
            <td>12 months</td>
            <td>$126,000</td>
          </tr>

          <tr>
            <td>Contract 2</td>
            <td>Monthly</td>
            <td>$6,000</td>
            <td>9 months</td>
            <td>$63,000</td>
          </tr>

        </tbody>

      </table>

    </div>
  );
};

export default ContractTable;