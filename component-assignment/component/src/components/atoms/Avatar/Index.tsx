interface AvatarProps {
  src: string;
  alt?: string;
  size?: number;
}

const Avatar = ({
  src,
  alt,
  size,
}: AvatarProps) => {
  return (
    <img
      src={src}
      alt={alt}
      width={size}
      height={size}
      className="avatar"
    />
  );
};

export default Avatar;
