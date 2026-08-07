import { FiChevronDown } from "react-icons/fi";

import Avatar from "../../atoms/Avatar/Avatar";
import Icon from "../../atoms/Icon/Icon";
import Typography from "../../atoms/Typography/Typography";

interface ProfileMenuProps {
  image: string;
  name: string;
}

const ProfileMenu = ({
  image,
  name,
}: ProfileMenuProps) => {
  return (
    <div className="profile-menu">
      <Avatar src={image} />

      <Typography
        text={name}
        variant="body"
      />

      <Icon icon={FiChevronDown} size={18} className="icon-white" />
    </div>
  );
};

export default ProfileMenu;
