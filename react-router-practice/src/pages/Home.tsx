import { useNavigate } from 'react-router-dom';

const Home = () => {
  const navigate = useNavigate();

  const goToStudents = () => {
    navigate('/students');
  };

  return (
    <div>
      <h1>Home Page</h1>
      <p>Welcome to the Student Portal.</p>

      <button onClick={goToStudents}>
        View Students
      </button>
    </div>
  );
};

export default Home;