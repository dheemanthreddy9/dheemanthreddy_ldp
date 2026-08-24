import "./App.css";

import avatar from "./assets/images/avatar.png";

import Typography from "./components/atoms/Typography";
import Icon from "./components/atoms/Icon";

import SidebarItem from "./components/molecules/SideBar";
import SummaryCard from "./components/molecules/SummaryCard";
import ActionCard from "./components/molecules/ActionCard";
import ProfileMenu from "./components/molecules/ProfileMenu";
import ContractTable from "./components/molecules/CashAceleration";
import { TEXTS } from "./utils/constants";

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
            size={30}
            className="icon-primary"
          />

          <Typography
            text={TEXTS.LOGO}
            variant="subtitle"
          />

        </div>

        <div className="sidebar-menu">

          <SidebarItem
            icon={FiHome}
            text={TEXTS.HOME}
          />

          <SidebarItem
            icon={FiLink2}
            text={TEXTS.CASH_ACCELERATION}
            active
          />

        </div>

        <div className="sidebar-footer">

          <SidebarItem
            icon={FiPlayCircle}
            text={TEXTS.WATCH_HOW_TO}
          />

        </div>

      </div>

      {/* Main */}

      <div className="main">

        <div className="header">

          <div>

            <Typography
              text={TEXTS.HEADER_TITLE}
              variant="title"
            />

            <Typography
              text={TEXTS.HEADER_SUBTITLE}
              variant="caption"
            />

          </div>

          <ProfileMenu
            image={avatar}
            name={TEXTS.USER_NAME}
          />

        </div>

        {/* Top Cards */}

        <div className="top-cards">

          <div className="summary-container">

            <SummaryCard
              icon={FiCalendar}
              title={TEXTS.TERM_CAP_TITLE}
              value={TEXTS.TERM_CAP_VALUE}
            />

            <SummaryCard
              icon={FiFileText}
              title={TEXTS.AVAILABLE_CREDIT_TITLE}
              value={TEXTS.AVAILABLE_CREDIT_VALUE}
            />

            <SummaryCard
              icon={FiPercent}
              title={TEXTS.MAX_INTEREST_RATE_TITLE}
              value={TEXTS.MAX_INTEREST_RATE_VALUE}
            />

          </div>

          <ActionCard
            title={TEXTS.ACTION_CARD_TITLE}
            description={TEXTS.ACTION_CARD_DESCRIPTION}
            buttonText={TEXTS.ACTION_CARD_BUTTON}
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
