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
  ACTION_CARD_CONSTANTS,
  APP_NAME,
  HEADER_SUBTITLE,
  HEADER_TITLE,
  NAV_ITEMS,
  SUMMARY_CARD_CONSTANTS,
  USER_NAME,
} from "./utils/constants";

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
            text={APP_NAME}
            variant="subtitle"
          />

        </div>

        <div className="sidebar-menu">

          <SidebarItem
            icon={FiHome}
            text={NAV_ITEMS.HOME}
          />

          <SidebarItem
            icon={FiLink2}
            text={NAV_ITEMS.CASH_ACCELERATION}
            active
          />

        </div>

        <div className="sidebar-footer">

          <SidebarItem
            icon={FiPlayCircle}
            text={NAV_ITEMS.WATCH_HOW_TO}
          />

        </div>

      </div>

      {/* Main */}

      <div className="main">

        <div className="header">

          <div>

            <Typography
              text={HEADER_TITLE}
              variant="title"
            />

            <Typography
              text={HEADER_SUBTITLE}
              variant="caption"
            />

          </div>

          <ProfileMenu
            image={avatar}
            name={USER_NAME}
          />

        </div>

        {/* Top Cards */}

        <div className="top-cards">

          <div className="summary-container">

            <SummaryCard
              icon={FiCalendar}
              title={SUMMARY_CARD_CONSTANTS.TERM_CAP.TITLE}
              value={SUMMARY_CARD_CONSTANTS.TERM_CAP.VALUE}
            />

            <SummaryCard
              icon={FiFileText}
              title={SUMMARY_CARD_CONSTANTS.AVAILABLE_CREDIT.TITLE}
              value={SUMMARY_CARD_CONSTANTS.AVAILABLE_CREDIT.VALUE}
            />

            <SummaryCard
              icon={FiPercent}
              title={SUMMARY_CARD_CONSTANTS.MAX_INTEREST_RATE.TITLE}
              value={SUMMARY_CARD_CONSTANTS.MAX_INTEREST_RATE.VALUE}
            />

          </div>

          <ActionCard
            title={ACTION_CARD_CONSTANTS.TITLE}
            description={ACTION_CARD_CONSTANTS.DESCRIPTION}
            buttonText={ACTION_CARD_CONSTANTS.BUTTON_TEXT}
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