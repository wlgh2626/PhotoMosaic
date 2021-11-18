export default function FileChooser({ setFile, setFileUrl }) {
  return (
    <input
      type="file"
      accept="image/*"
      onChange={(event) => {
        setFile(event.target.files[0]);
        setFileUrl(URL.createObjectURL(event.target.files[0]));
      }}
    />
  );
}
