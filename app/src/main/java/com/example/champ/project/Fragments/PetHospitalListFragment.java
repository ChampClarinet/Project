package com.example.champ.project.Fragments;

public class PetHospitalListFragment /*extends Fragment implements SearchView.OnQueryTextListener */{
/*
    private static final String TAG = PetHospitalListFragment.class.getSimpleName();

    @BindView(R.id.rv_hospital)
    RecyclerView mRecyclerViewHospital;
    private SearchView searchView;

    private ServiceRecyclerViewAdapter adapter;
    private HospitalMenu menu;

    public PetHospitalListFragment() {
        menu = HospitalMenu.getInstance(getContext());
    }

    public static PetHospitalListFragment newInstance() {
        PetHospitalListFragment fragment = new PetHospitalListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main, container, false);
        searchView = v.findViewById(R.id.searchView);
        Log.d(TAG, searchView+"");
        return inflater.inflate(R.layout.fragment_pet_hospital, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        searchView.setOnQueryTextListener(this);
        mRecyclerViewHospital.setHasFixedSize(true);
        mRecyclerViewHospital.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ServiceRecyclerViewAdapter(menu.getHospitalList(HospitalMenu.SORT_BY_DISTANCE), getContext(), getString(R.string.model_name_hospital), (SearchView) getActivity().findViewById(R.id.searchView));
        mRecyclerViewHospital.setAdapter(adapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ArrayList<PetService> newList = menu.getFilteredHospitalList(newText);
        adapter.updateList(newList);
        return false;
    }
*/
}
