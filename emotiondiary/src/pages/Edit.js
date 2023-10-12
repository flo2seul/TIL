import { useNavigate, useSearchParams } from "react-router-dom";

const Edit = () => {
  const navigate = useNavigate();
  const [searchParams, setSearchParams] = useSearchParams();

  const id = searchParams.get("id");
  console.log(id);
  return (
    <div>
      <h1>New</h1>
      <p>이곳은 일기 수정 페이지입니다.</p>
      <button
        onClick={() => {
          setSearchParams({ id: "wtf" });
        }}
      >
        hi
      </button>
      <button
        onClick={() => {
          navigate("/home");
        }}
      >
        Home
      </button>
      <button
        onClick={() => {
          navigate(-1);
        }}
      >
        뒤로가기
      </button>
    </div>
  );
};

export default Edit;
