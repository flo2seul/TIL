import { useParams, useNavigate } from "react-router-dom";
import { useContext, useEffect, useState } from "react";
import { DiaryStateContent } from "../App";
import { getStringDate } from "../utils/date";
import { emotionList } from "../utils/emotion";
import MyHeader from "../components/MyHeader";
import MyButton from "../components/MyButton";

const Diary = () => {
  const { id } = useParams();
  const diaryList = useContext(DiaryStateContent);
  const [originData, setOriginData] = useState("");
  const navigate = useNavigate();
  useEffect(() => {
    if (diaryList.length >= 1) {
      const targetDiary = diaryList.find(
        (it) => parseInt(it.id) === parseInt(id)
      );

      if (targetDiary) {
        //일기가 존재할 때
        setOriginData(targetDiary);
      } else {
        alert("없는 일기입니다.");
        navigate("/", { replace: true });
      }
    }
  }, [id, diaryList]);

  const goEdit = () => {
    navigate(`/edit/${id}`);
  };

  const date = originData.date
    ? getStringDate(new Date(parseInt(originData.date)))
    : "";

  if (!originData) {
    return <div className="DiaryPage">로딩중입니다.</div>;
  } else {
    const curEmotionData = emotionList.find(
      (it) => parseInt(it.emotion_id) === parseInt(originData.emotion)
    );
    console.log(originData, curEmotionData);
    return (
      <div className="DiaryPage">
        <MyHeader
          headText={`${date} 기록`}
          leftChild={
            <MyButton
              text={"< 뒤로가기"}
              onClick={() => {
                navigate(-1);
              }}
            />
          }
          rightChild={<MyButton text={"수정하기"} onClick={goEdit} />}
        />
        <article>
          <section>
            <h4>오늘의 감정</h4>
            <div
              className={[
                "diary_img_wrapper",
                `emotion_img_wrapper_${curEmotionData.emotion_id}`,
              ].join(" ")}
            >
              <img src={curEmotionData.emotion_img} />
              <div className="emotion_descript">
                {curEmotionData.emotion_descript}
              </div>
            </div>
          </section>
          <section>
            <h4>오늘의 일기</h4>
            <div className="content_wrapper">
              <p>{originData.content}</p>
            </div>
          </section>
        </article>
      </div>
    );
  }
};

export default Diary;
