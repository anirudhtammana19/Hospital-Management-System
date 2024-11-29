import React, { useState } from "react";
import "./CustomCarousel.css";

const CustomCarousel = ({ items }) => {
  const [activeIndex, setActiveIndex] = useState(0);

  const slideNext = () => {
    setActiveIndex((prevIndex) => {
      // Ensure the slider doesn't go beyond the last set of visible items
      const maxIndex = Math.ceil(items.length / 3) - 1;
      return prevIndex >= maxIndex ? 0 : prevIndex + 1;
    });
  };

  const slidePrev = () => {
    setActiveIndex((prevIndex) => {
      return prevIndex <= 0 ? Math.ceil(items.length / 3) - 1 : prevIndex - 1;
    });
  };

  return (
    <div className="multi-item-carousel">
      <button className="carousel-btn prev-btn" onClick={slidePrev}>
        {"<"}
      </button>
      <div className="carousel-track">
        {items.map((item, index) => (
          <div
            key={index}
            className={`carousel-item ${
              Math.floor(index / 3) === activeIndex ? "visible" : "hidden"
            }`}
          >
            {item}
          </div>
        ))}
      </div>
      <button className="carousel-btn next-btn" onClick={slideNext}>
        {">"}
      </button>
    </div>
  );
};

export default CustomCarousel;
