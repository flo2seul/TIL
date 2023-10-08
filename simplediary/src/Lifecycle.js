import React, { useEffect, useState } from "react";

const Lifecycle = () => {
  const [count, setCount] = useState(0);
  const [text, setText] = useState("");

  useEffect(() => {
    console.log("Mount:");
  }, []); // dependancy area

  useEffect(() => {
    console.log("Update:");
  });

  useEffect(() => {
    console.log(`count is update : ${count}`);
  }, [count]);

  useEffect(() => {
    console.log(`count is update : ${text}`);
  }, [text]);

  // useEffect(() => {
  //   console.log("Mount:");
  //   return () => {
  //     console.log("Unmount");
  //   };
  // }, []);
  return (
    <div style={{ padding: 20 }}>
      <div>
        {count}
        <button onClick={() => setCount(count + 1)}>+</button>
      </div>
      <div>
        <input
          value={text}
          onChange={(e) => {
            setText(e.target.value);
          }}
        ></input>
      </div>
    </div>
  );
};

export default Lifecycle;
