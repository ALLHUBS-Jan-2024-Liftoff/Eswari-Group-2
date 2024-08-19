import React, { useState, useEffect} from "react";
import Button from "react-bootstrap/Button"


const likeButton = () => {
    //state
    const [count, setCount] = useState(0);

    const handleClick = () => {
        setCount(count + 1); 
    }
    //


    return(
        <>
        <Button onClick={handleClick}>Like</Button>
        <p>{count}</p>
        </>
    )
}

export default likeButton;