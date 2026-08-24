import { DEFAULT_AVATAR } from '../assets/images';

export const BRAND_NAME = 'RECRUIT';

export const defaultUser = {
  name: 'James Rodriguez',
  email: 'James.co',
  avatarUrl: DEFAULT_AVATAR,
};

export const SENDER_EMAIL = 'Kyle@Checkr.com';
export const COMPANY_NAME = 'Checkr-bpo';

export const DEFAULT_AUTO_SEND_DAYS = 7;
export const DEFAULT_PER_PAGE = 10;
export const TOTAL_RESULTS_COUNT = 84;

export const formatCandidateEmail = (name: string): string => {
  if (!name) return `candidate@Checkr.com`;
  const formattedName = name
    .trim()
    .split(/\s+/)
    .map((word) => word.charAt(0).toUpperCase() + word.slice(1).toLowerCase())
    .join('.');
  return `${formattedName}@Checkr.com`;
};

// UI Text & Header Constants
export const CANDIDATES_PAGE_TITLE = 'Candidates';
export const EXPORT_BUTTON_LABEL = 'Export';
export const MANUAL_ORDER_BUTTON_LABEL = 'Manual Order';
export const CANDIDATE_INFO_TITLE = 'Candidate Information';
export const SEARCH_PLACEHOLDER = 'Search any candidate';
export const DATE_PICKER_PLACEHOLDER = 'Select date';
export const PAGINATION_OUT_OF_LABEL = 'out of';
export const PAGINATION_RESULTS_LABEL = 'results';
export const PAGINATION_PER_PAGE_LABEL = 'per page';
export const ADJUDICATION_ADVERSE_ACTION = 'ADVERSE ACTION';
export const STATUS_CONSIDER = 'CONSIDER';
export const FILTER_BUTTON_LABEL = 'Filter';
export const CANDIDATES_TABLE_HEADERS = ['NAME', 'ADJUDICATION', 'STATUS', 'LOCATION', 'DATE'];

export const PRE_ADVERSE_ACTION_BUTTON_LABEL = 'Pre-Adverse Action';
export const ENGAGE_BUTTON_LABEL = 'Engage';
export const REPORT_INFO_TITLE = 'Report Information';
export const COURT_SEARCHES_TITLE = 'Court Searches';
export const COURT_SEARCHES_TABLE_HEADERS = ['SEARCH', 'STATUS', 'DATE'];

export const PRE_ADVERSE_NOTICE_TITLE = 'Pre-Adverse Action Notice';
export const CHARGES_SELECT_TITLE = 'Select The Charges For The Pre adverse Action';
export const AUTO_SEND_LABEL_1 = 'Auto send post adverse action';
export const AUTO_SEND_LABEL_2 = 'Days';
export const PREVIEW_NOTICE_BUTTON_LABEL = 'Preview Notice';
export const SUBMIT_NOTICE_BUTTON_LABEL = 'Submit Notice';
export const ATTACHMENTS_TITLE = 'Attachments';

export const FROM_LABEL = 'From:';
export const TO_LABEL = 'To:';
export const SUBJECT_LABEL = 'Subject:';
export const SINCERELY_LABEL = 'Sincerely,';

export const EMAIL_SUBJECT = `Pre-Adverse Action Notice - ${COMPANY_NAME}`;

export const EMAIL_PARAGRAPH_1 =
  'You recently authorized checkr-bpo ("the company") to obtain consumer reports and/or investigate consumer reports about you from a consumer reporting agency. The Company is considering taking action in whole or in past on information in such report(s) including the following specific items identified in the report prepared by Checkr, Inc.';

export const EMAIL_PARAGRAPH_2 =
  'If you wish to dispute the accuracy of the information in the report directly with the consumer reporting agency (i.e., the source of the information contained in the report), you should contact the agency identified above directly.';

export const WARNING_ALERTS = [
  'Please carefully review the list of charges (in bold) and your contact information.',
  'Please note that we will send the corresponding post adverse action email automatically after 7 days.',
];

export const FCRA_ATTACHMENTS = [
  'Summary of right under the FCRA',
  'Copy of background report',
];

export const initialCandidates = [
  { id: '1', name: 'John Smith', adjudication: '-', status: 'CLEAR', location: 'Barrouallie', date: '2/22/2022' },
  { id: '2', name: 'Serene', adjudication: '-', status: 'CLEAR', location: 'Vänersborg', date: '3/13/2022' },
  { id: '3', name: 'Walsh', adjudication: '-', status: 'CONSIDER', location: 'Sukamanah', date: '7/2/2022' },
  { id: '4', name: 'Maurizia', adjudication: '-', status: 'CLEAR', location: 'Sukamanah', date: '2/20/2022' },
  { id: '5', name: 'Kendre', adjudication: '-', status: 'CLEAR', location: 'Beutong Ateuh', date: '5/19/2022' },
  { id: '6', name: 'Erastus', adjudication: '-', status: 'CLEAR', location: 'Höviyn Am', date: '12/1/2022' },
  { id: '7', name: 'Jereme', adjudication: '-', status: 'CONSIDER', location: 'Sharingol', date: '7/26/2022' },
  { id: '8', name: 'John Smith', adjudication: '-', status: 'CONSIDER', location: 'Lianyun', date: '5/28/2022' },
  { id: '9', name: 'Cari', adjudication: '-', status: 'CLEAR', location: 'Taboão da Serra', date: '5/23/2022' },
  { id: '10', name: 'Kimble', adjudication: '-', status: 'CONSIDER', location: 'Veseli nad Moravou', date: '8/24/2022' },
];

export const chargesList = [
  'Driving while license suspended',
  'Assault Domestic Violence',
  'Unable to verify employment history at Dunder Mifflin',
];

export const courtSearches = [
  { id: '1', search: 'SSN Verification', status: 'CLEAR', date: '2/22/2022' },
  { id: '2', search: 'Sex Offender', status: 'CLEAR', date: '3/13/2022' },
  { id: '3', search: 'Global Watchlist', status: 'CONSIDER', date: '7/2/2022' },
  { id: '4', search: 'Federal Criminal', status: 'CLEAR', date: '2/20/2022' },
  { id: '5', search: 'County Criminal', status: 'CLEAR', date: '5/19/2022' },
];

export const SIDEBAR_NAV_ITEMS = [
  { label: 'Home', page: 'home', iconKey: 'GridView' },
  { label: 'Candidates', page: 'page1', iconKey: 'Group' },
  { label: 'Adverse Actions', page: 'page3', iconKey: 'ReportProblem' },
  { label: 'Logs', page: 'logs', iconKey: 'Description' },
  { label: 'Analytics', page: 'analytics', iconKey: 'InsertChart' },
  { label: 'Account', page: 'account', iconKey: 'Person' },
  { label: 'Screenings', page: 'screenings', iconKey: 'Assignment' },
];
