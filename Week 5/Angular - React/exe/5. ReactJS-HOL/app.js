import CohortDetails from "./CohortDetails";

function App() {
  return (
    <div>
      <CohortDetails
        name="React"
        trainer="Shree"
        status="ongoing"
        strength="30"
      />
      <CohortDetails
        name="Angular"
        trainer="Petty"
        status="completed"
        strength="25"
      />
      <CohortDetails
        name="Java"
        trainer="Keerthu"
        status="ongoing"
        strength="35"
      />
    </div>
  );
}

export default App;
