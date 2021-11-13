import download from "../../icons/download.png";
import React from "react";

import "./download.css";
export default function Download({ targetImageURL }) {
  return (
    <a href={targetImageURL} download>
      <button className="download-button">Download</button>
    </a>
  );
}
