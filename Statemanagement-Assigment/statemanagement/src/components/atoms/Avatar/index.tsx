import React from 'react';
import { DEFAULT_AVATAR } from '../../../assets/images';

interface AvatarProps {
  src?: string;
  alt?: string;
  className?: string;
}

export const Avatar: React.FC<AvatarProps> = ({
  src = DEFAULT_AVATAR,
  alt = 'User Avatar',
  className = '',
}) => {
  return (
    <img
      src={src}
      alt={alt}
      className={`avatar-img ${className}`}
    />
  );
};
