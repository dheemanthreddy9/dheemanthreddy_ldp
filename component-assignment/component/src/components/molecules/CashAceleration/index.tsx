import Typography from "../../atoms/Typography";
import { TEXTS, CONTRACT_TABLE_HEADERS, MOCK_CONTRACTS } from "../../../utils/constants";

const ContractTable = () => {
  return (
    <div className="contract-table">

      <div className="table-header">

        <Typography text={TEXTS.YOUR_FUNDING} variant="subtitle" />

        <div className="tabs">
          <button className="tab active-tab">{TEXTS.MY_CONTRACTS}</button>

          <button className="tab">{TEXTS.MY_CASH_KICKS}</button>
        </div>

      </div>

      <table>

        <thead>

          <tr>
            {CONTRACT_TABLE_HEADERS.map((header) => (
              <th key={header}>{header}</th>
            ))}
          </tr>

        </thead>

        <tbody>

          {MOCK_CONTRACTS.map((contract) => (
            <tr key={contract.id}>
              <td>{contract.name}</td>
              <td>{contract.type}</td>
              <td>{contract.perPayment}</td>
              <td>{contract.termLength}</td>
              <td>{contract.paymentRemaining}</td>
            </tr>
          ))}

        </tbody>

      </table>

    </div>
  );
};

export default ContractTable;
