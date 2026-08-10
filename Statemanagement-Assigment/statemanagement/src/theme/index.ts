export const colors = {
  primary: '#224DFF',
  primaryHover: '#1A3ECC',
  accentLightYellow: '#FAF8EB',
  badgeConsiderText: '#92400E',
  badgeClearBg: '#E6F4EA',
  badgeClearText: '#137333',
  background: '#F7F9FC',
  cardBg: '#FFFFFF',
  textPrimary: '#0F172A',
  textSecondary: '#64748B',
  border: '#E2E8F0',
};

export const typography = {
  fontFamily: "'Inter'",
  h1: { fontFamily: "'Inter'" },
  h2: { fontFamily: "'Inter'" },
  h3: { fontFamily: "'Inter'" },
  h4: { fontFamily: "'Inter'" },
  h5: {
    fontFamily: "'Inter'",
    fontWeight: 500,
    fontSize: '20px',
    lineHeight: '30px',
    letterSpacing: '0%',
    verticalAlign: 'middle',
  },
  h6: { fontFamily: "'Inter'" },
  subtitle1: { fontFamily: "'Inter'" },
  subtitle2: { fontFamily: "'Inter'", fontWeight: 500, fontSize: '16px', lineHeight: '24px' },
  body1: {
    fontFamily: "'Inter'",
    fontWeight: 500,
    fontSize: '16px',
    lineHeight: '24px',
    letterSpacing: '0%',
    verticalAlign: 'middle',
  },
  body2: {
    fontFamily: "'Inter'",
    fontWeight: 400,
    fontSize: '14px',
    lineHeight: '20px',
    letterSpacing: '0%',
  },
  caption: { fontFamily: "'Inter'" },
  button: { fontFamily: "'Inter'" },
  overline: { fontFamily: "'Inter'" },
};

export const layout = {
  card: {
    borderRadius: '6px',
    boxShadow: '0px 4px 28px rgba(0, 0, 0, 0.05)',
  },
  header: {
    height: '36px',
    borderRadius: '6px',
    border: '1px solid #E2E8F0',
  },
  button: {
    height: '36px',
    borderRadius: '6px',
    padding: '8px 16px',
    gap: '4px',
  },
  badge: {
    height: '26px',
    borderRadius: '4px',
    border: '1px solid transparent',
    padding: '4px 12px',
    gap: '10px',
  },
};

export const theme = {
  colors,
  typography,
  layout,
};

export default theme;
