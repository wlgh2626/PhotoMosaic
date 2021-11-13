import github from "../../icons/github.png";
import "./footer.css"
export default function Footer(){
    return (
        <div className="footer-container">
            <label className="author-name">Made By Jiho Shin</label>
            <a href="https://github.com/wlgh2626/PhotoMosaic"> 
                <img className="icons" src={github} alt="github-icon"/> 
            </a>
        </div>
    )
}