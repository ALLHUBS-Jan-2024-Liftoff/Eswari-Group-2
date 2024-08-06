// TODO Requires handle submit logic
// TODO link when registration approved to notifications
import React, {useState} from "react";
import { registerUser } from "../../services/userService";


export const SignUpForm = () => {

    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [verifyPassword, setVerifyPassword] = useState("");
    const [role, setRole] = useState("");
    const [error, setError] = useState(null);
    const [successMsg, setSuccessMsg] = useState(null)

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await registerUser(username, email, password, verifyPassword, role);
            setError(null);
            setSuccessMsg("Registration successful!");
        } catch (error) {
            setSuccessMsg(null);
            setError("Registration failed.");
        }
    };
    
    return(
        <div>
            <h2>Welcome to gitArtsy!</h2>
            <h3>Create an account:</h3>
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label>Username:
                        <input type="text" name="username" value={username} id="username" onChange={(e) => setUsername(e.target.value)} required></input>
                        </label>
                    </div>
                    <div className="form-group">
                        <label>Email:
                        <input type="text" name="email" value={email} id="email" onChange={(e) => setEmail(e.target.value)} required></input>
                        </label>
                    </div>
                    <div className="form-group">
                        <label>Password:
                        <input type="password" name="password" value={password} id="password" onChange={(e) => setPassword(e.target.value)} required></input>
                        </label>
                    </div>
                    <div className="form-group">
                        <label>Verify Password:
                        <input type="password" name="verify" value={verifyPassword} id="verify" onChange={(e) => setVerifyPassword(e.target.value)} required>
                        {/* Include verify password logic */}
                        </input>
                        </label>
                    </div>
                    {/* <div className="form-group">
                        <label>Displayed Name:
                        <input type="text" name="name" id="name"></input>
                        </label>
                    </div> */}
                    <div className="form-group">
                        {/* Add logic for one type must be selected */}
                        <label for="user-type">Account Type:
                            <label>  
                            <input type="radio" name="user-type" value="ARTIST" id="artist" onChange={(e) => setRole(e.target.value)}></input>
                        Artist</label>
                            
                            <label for="patron">  
                            <input type="radio" name="user-type" value="PATRON" id="patron" onChange={(e) => setRole(e.target.value)}></input>
                        Patron</label>
                        </label>
                    </div>
                    <button type='submit'>Submit</button>
                </form>
        </div>
    )
}

export default SignUpForm