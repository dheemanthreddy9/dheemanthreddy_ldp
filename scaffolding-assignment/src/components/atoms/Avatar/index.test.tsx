import { render, screen } from '@testing-library/react';
import { describe, it, expect } from 'vitest';
import Avatar from './index';

describe('Avatar Atom Component', () => {
  it('renders avatar with alt text', () => {
    render(<Avatar alt="User Avatar" src="avatar.png" />);
    expect(screen.getByAltText('User Avatar')).toBeInTheDocument();
  });
});
