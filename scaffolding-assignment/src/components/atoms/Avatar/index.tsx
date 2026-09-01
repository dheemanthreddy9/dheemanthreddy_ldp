import React from 'react';
import MuiAvatar from '@mui/material/Avatar';
import type { AvatarProps as MuiAvatarProps } from '@mui/material/Avatar';

export interface AvatarProps extends MuiAvatarProps {
  src?: string;
  alt?: string;
  size?: number;
  className?: string;
}

const Avatar: React.FC<AvatarProps> = ({ src, alt, size, className, ...props }) => {
  return (
    <MuiAvatar
      src={src}
      alt={alt}
      className={className}
      {...props}
    />
  );
};

export default Avatar;
