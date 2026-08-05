import "./App.css";

import avatar from "./assets/images/avatar.png";

import Typography from "./components/atoms/Typography/Typography";
import Icon from "./components/atoms/Icon/Icon";

import SidebarItem from "./components/molecules/SideBar/SideBar";
import SummaryCard from "./components/molecules/SummaryCard/SummaryCard";
import ActionCard from "./components/molecules/ActionCard/Actioncard";
import ProfileMenu from "./components/molecules/ProfileMenu/ProfileMenu";
import ContractTable from "./components/molecules/CashAceleration/CashAceleration";

import {
  FiHome,
  FiLink2,
  FiPlayCircle,
  FiCalendar,
  FiFileText,
  FiPercent,
} from "react-icons/fi";

import { PiSquaresFourFill } from "react-icons/pi";

const App = () => {
  return (
    <div className="dashboard">

      {/* Sidebar */}

      <div className="sidebar">

        <div className="logo">

          <Icon
            icon={PiSquaresFourFill}
            className="logo__icon"
          />

          <Typography
            text="Seeder"
            variant="subtitle"
          />

        </div>

        <div className="sidebar-menu">

          <SidebarItem
            icon={FiHome}
            text="Home"
          />

          <SidebarItem
            icon={FiLink2}
            text="Cash Acceleration"
            active
          />

        </div>

        <div className="sidebar-footer">

          <SidebarItem
            icon={FiPlayCircle}
            text="Watch How To"
          />

        </div>

      </div>

      {/* Main */}

      <div className="main">

        <div className="header">

          <div>

            <Typography
              text="Cash acceleration"
              variant="title"
            />

            <Typography
              text="Place to create new cash kicks to run your business"
              variant="caption"
            />

          </div>

          <ProfileMenu
            image={avatar}
            name="Jessie Wilson"
          />

        </div>

        {/* Top Cards */}

        <div className="top-cards">

          <div className="summary-container">

            <SummaryCard
              icon={FiCalendar}
              title="Term cap"
              value="12 Months"
            />

            <SummaryCard
              icon={FiFileText}
              title="Available Credit"
              value="$709.45k"
            />

            <SummaryCard
              icon={FiPercent}
              title="Max Interest Rate"
              value="12.00%"
            />

          </div>

          <ActionCard
            title="Launch a new Cash Kick"
            description="You have upto $709,546 available for a new cash advance."
            buttonText="New Cash Kick"
          />

        </div>

        <div className="table-section">

          <ContractTable />

        </div>

      </div>

    </div>
  );
};

export default App;