export default function FileChooser({ setFile, setFileUrl }) {
  function selectFile(files) {
    if (files.length == 1) {
      setFile(files[0]);
      setFileUrl(URL.createObjectURL(files[0]));
    } else {
      setFile(files);
    }
  }

  return (
    <label>
      Choose Photos
      <input
        type="file"
        accept="image/*"
        onChange={(event) => {
          selectFile(event.target.files);
        }}
        hidden
      />
    </label>
  );
}
