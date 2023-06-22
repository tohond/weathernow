import "./WeatherDetails.css";
import moment from "moment";
import { TiLocationOutline } from "react-icons/ti";

export default function WeatherDetails({ weather }) {
  return (
    <section className="weather-container">
      <div className="container-top">
        <div>
          <h1 className="weather-title">Today</h1>
          <p className="paragraph">
            {moment(weather?.location.localtime).format("h:mm a")}
          </p>
        </div>
        <p className="paragraph">
          {moment(weather?.location.localtime).format("MMM Do, YYYY")}
        </p>
        <p className="paragraph">
          {weather?.current.condition.text}
        </p>
      </div>
      <div className="container-mid">
        <span className="weather-temp-main">
          {weather?.current.temp_c} <span className="weather-tepm">°C</span>
        </span>
        <img
          width="64"
          height="64"
          src={weather?.current.condition.icon}
          alt={`Icon of ${weather?.location.country}`}
        />
      </div>
      <div className="container-bot">
        <TiLocationOutline className="weather-icon-location" />
        <p className="paragraph">
          {weather?.location.country}, {weather?.location.name}
        </p>
      </div>
    </section>
  );
}
